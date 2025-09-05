package com.enomyfinances.service;
import com.enomyfinances.model.User;
import com.enomyfinances.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // Now correctly injected
    public User registerUser(User user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash())); // Hash password
        return userRepository.saveUser(user);
    }
}