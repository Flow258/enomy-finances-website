package com.enomyfinances.dao;

import com.enomyfinances.model.NotUseProfile;
import com.enomyfinances.utils.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class NotUseProfileDAO {
    private static final Logger logger = LoggerFactory.getLogger(NotUseProfileDAO.class);

    private static final String INSERT_PROFILE_SQL = 
        "INSERT INTO profiles (user_id, phone_number, address, city, state, postal_code, country, date_of_birth, profile_picture_url) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String UPDATE_PROFILE_SQL = 
        "UPDATE profiles SET phone_number=?, address=?, city=?, state=?, postal_code=?, country=?, date_of_birth=?, profile_picture_url=? " +
        "WHERE user_id=?";
    
    private static final String SELECT_PROFILE_BY_USER_ID = 
        "SELECT * FROM profiles WHERE user_id=?";

    // Create or update profile
    public boolean saveProfile(NotUseProfile profile) {
        if (profile == null || profile.getUser() == null) {
            logger.error("Invalid profile data provided");
            return false;
        }

        // Check if profile exists
        NotUseProfile existingProfile = getProfileByUserId(profile.getUser().getUserId());
        if (existingProfile != null) {
            return updateProfile(profile);
        } else {
            return createProfile(profile);
        }
    }

    // Create new profile
    private boolean createProfile(NotUseProfile profile) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROFILE_SQL)) {
            
            setProfileParameters(preparedStatement, profile);
            preparedStatement.setInt(1, profile.getUser().getUserId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error creating profile: {}", e.getMessage(), e);
            return false;
        }
    }

    // Update existing profile
    private boolean updateProfile(NotUseProfile profile) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROFILE_SQL)) {
            
            setProfileParameters(preparedStatement, profile);
            preparedStatement.setInt(9, profile.getUser().getUserId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error updating profile: {}", e.getMessage(), e);
            return false;
        }
    }

    // Get profile by user ID
    public NotUseProfile getProfileByUserId(int userId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROFILE_BY_USER_ID)) {
            
            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToProfile(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving profile: {}", e.getMessage(), e);
        }
        return null;
    }

    // Helper method to set prepared statement parameters
    private void setProfileParameters(PreparedStatement preparedStatement, NotUseProfile profile) throws SQLException {
        preparedStatement.setString(2, profile.getPhoneNumber());
        preparedStatement.setString(3, profile.getAddress());
        preparedStatement.setString(4, profile.getCity());
        preparedStatement.setString(5, profile.getState());
        preparedStatement.setString(6, profile.getPostalCode());
        preparedStatement.setString(7, profile.getCountry());
        preparedStatement.setDate(8, profile.getDateOfBirth());
        preparedStatement.setString(9, profile.getProfilePictureUrl());
    }

    // Helper method to map ResultSet to Profile object
    private NotUseProfile mapResultSetToProfile(ResultSet resultSet) throws SQLException {
        NotUseProfile profile = new NotUseProfile();
        profile.setProfileId(resultSet.getInt("profile_id"));
        profile.setPhoneNumber(resultSet.getString("phone_number"));
        profile.setAddress(resultSet.getString("address"));
        profile.setCity(resultSet.getString("city"));
        profile.setState(resultSet.getString("state"));
        profile.setPostalCode(resultSet.getString("postal_code"));
        profile.setCountry(resultSet.getString("country"));
        profile.setDateOfBirth(resultSet.getDate("date_of_birth"));
        profile.setProfilePictureUrl(resultSet.getString("profile_picture_url"));
        return profile;
    }
}