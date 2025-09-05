package com.enomyfinances.dao;

import com.enomyfinances.model.User;
import com.enomyfinances.model.User.Role;
import com.enomyfinances.model.User.Status;
import com.enomyfinances.utils.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    private static final String INSERT_USER_SQL = 
        "INSERT INTO users (email, password_hash, full_name, role, created_at, status, two_factor_enabled) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_EMAIL = 
        "SELECT * FROM users WHERE email = ?";
    private static final String UPDATE_LAST_LOGIN = 
        "UPDATE users SET last_login = ? WHERE user_id = ?";
    private static final String UPDATE_PASSWORD =
    	    "UPDATE users SET password_hash = ?, salt = ? WHERE user_id = ?";


    // Register a new user
    public boolean registerUser(User user) {
        if (user == null || user.getEmail() == null || user.getPasswordHash() == null) {
            logger.error("Invalid user data provided for registration");
            return false;
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPasswordHash());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, user.getRole().name());
            preparedStatement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(6, user.getStatus() != null ? user.getStatus().name() : Status.ACTIVE.name());
            preparedStatement.setBoolean(7, user.isTwoFactorEnabled());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error registering user: {}", e.getMessage(), e);
            return false;
        }
    }

    // Login a user
    public User loginUser(String email, String passwordHash) {
        if (email == null || passwordHash == null) {
            logger.error("Email or password hash is null");
            return null;
        }

        User user = null;
        Connection connection = null;

        try {
            connection = DatabaseConnection.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
                preparedStatement.setString(1, email);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Verify password hash matches
                        String storedHash = resultSet.getString("password_hash");
                        if (!passwordHash.equals(storedHash)) {
                            logger.warn("Invalid password attempt for user: {}", email);
                            return null;
                        }

                        user = new User();
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setEmail(resultSet.getString("email"));
                        user.setPasswordHash(resultSet.getString("password_hash"));
                        user.setFullName(resultSet.getString("full_name"));
                        user.setRole(Role.valueOf(resultSet.getString("role")));
                        user.setCreatedAt(resultSet.getTimestamp("created_at"));
                        user.setLastLogin(resultSet.getTimestamp("last_login"));
                        user.setStatus(resultSet.getString("status") != null ? 
                            Status.valueOf(resultSet.getString("status")) : Status.ACTIVE);
                        user.setTwoFactorEnabled(resultSet.getBoolean("two_factor_enabled"));

                        // Update last login time
                        updateLastLogin(user.getUserId(), connection);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error during user login: {}", e.getMessage(), e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Error closing connection: {}", e.getMessage(), e);
                }
            }
        }

        return user;
    }

    private void updateLastLogin(int userId, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LAST_LOGIN)) {
            preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating last login time: {}", e.getMessage(), e);
        }
    }
    
    /**
     * Updates a user's password and salt.
     * 
     * @param userId The ID of the user
     * @param newPasswordHash The new password hash
     * @param salt The new salt
     * @return True if the update was successful, false otherwise
     */
    public boolean updatePassword(int userId, String newPasswordHash, String salt) {
        if (newPasswordHash == null || salt == null) {
            logger.error("New password hash or salt is null");
            return false;
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD)) {
            
            preparedStatement.setString(1, newPasswordHash);
            preparedStatement.setString(2, salt);
            preparedStatement.setInt(3, userId);
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("Error updating password for user ID {}: {}", userId, e.getMessage(), e);
            return false;
        }
    }

}