package com.enomyfinances.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Utility class for handling password hashing and verification.
 */
public class PasswordUtils {
    private static final Logger logger = LoggerFactory.getLogger(PasswordUtils.class);
    private static final String HASH_ALGORITHM = "SHA-256";
    private static final int SALT_LENGTH = 16;

    /**
     * Generates a secure random salt.
     *
     * @return Base64 encoded salt string
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    /**
     * Hash a password with a specific salt.
     *
     * @param password The password to hash
     * @param salt     The salt to use
     * @return The salted and hashed password
     */
    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
            md.update(salt.getBytes(StandardCharsets.UTF_8)); // Add salt to hashing process
            byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error hashing password with salt: {}", e.getMessage(), e);
            throw new RuntimeException("Error hashing password with salt", e);
        }
    }

    /**
     * Hash a password and return both hash and salt.
     *
     * @param password The password to hash
     * @return A string array: [0] = hashed password, [1] = salt
     */
    public static String[] hashPasswordWithSalt(String password) {
        String salt = generateSalt();
        String hashedPassword = hashPassword(password, salt);
        return new String[]{hashedPassword, salt};
    }

    /**
     * Verifies if the provided password matches the stored hash when a salt is used.
     *
     * @param password   The password to verify
     * @param storedHash The stored hash to compare against
     * @param salt       The salt used in the hash
     * @return True if the password matches, false otherwise
     */
    public static boolean verifyPassword(String password, String storedHash, String salt) {
        String hashedPassword = hashPassword(password, salt);
        return hashedPassword.equals(storedHash);
    }
}
