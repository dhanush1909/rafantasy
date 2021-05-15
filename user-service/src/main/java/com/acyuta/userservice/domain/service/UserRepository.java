package com.acyuta.userservice.domain.service;

import com.acyuta.userservice.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User Repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Find a user with matching Username.
     *
     * @param username The username.
     * @return User with matching username, if present.
     */
    Optional<User> findByUsername(String username);

    /**
     * To check with a user is present with an email or a username.
     *
     * @param email    The email.
     * @param username The username.
     * @return True/False depending on whether the user exists.
     */
    Boolean existsByEmailOrUsername(String email, String username);

    /**
     * To fetch a user with matching email or a username.
     *
     * @param username The username.
     * @param email    The email.
     * @return The user, if present.
     */
    Optional<User> findByUsernameOrEmail(String username, String email);

    /**
     * Find a user with matching token.
     *
     * @param token The unique token required during signup/forgot-password request.
     * @return The user, if present.
     */
    Optional<User> findByToken(String token);

    /**
     * Find a user with matching username and token.
     *
     * @param username The username.
     * @param token    The token.
     * @return The user, if present.
     */
    Optional<User> findByUsernameAndToken(String username, String token);
}
