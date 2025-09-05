package org.springframework.security.crypto.bcrypt;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptPasswordEncoder {

    /**
     * Encodes a raw password using BCrypt hashing.
     *
     * @param password the raw password to encode
     * @return the hashed password
     */
    public String encode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12)); // Use a strength of 12 for the salt
    }

    /**
     * Verifies if a raw password matches the hashed password.
     *
     * @param rawPassword     the raw password to check
     * @param hashedPassword  the hashed password to compare against
     * @return true if the raw password matches the hashed password, false otherwise
     */
    public boolean matches(String rawPassword, String hashedPassword) {
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }
}