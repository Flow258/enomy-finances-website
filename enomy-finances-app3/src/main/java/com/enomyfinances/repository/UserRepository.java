package com.enomyfinances.repository;

import com.enomyfinances.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
@Transactional
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Find user by email.
     * @param email User email.
     * @return Optional<User> if found, empty otherwise.
     */
    public Optional<User> findByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery(
            "SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty(); // Avoid NoResultException
        }
    }

    /**
     * Save or update user.
     * @param user User entity.
     * @return Saved User entity.
     */
    public User saveUser(User user) {
            return entityManager.merge(user); // Update existing user
        }
    }

