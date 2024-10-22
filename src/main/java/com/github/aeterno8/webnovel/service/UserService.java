package com.github.aeterno8.webnovel.service;

import com.github.aeterno8.webnovel.models.User;

import java.util.Optional;

public interface UserService {

    /**
     * Finds a user by their unique identifier.
     *
     * @param id the unique identifier of the user
     * @return an {@link Optional} containing the found user, or empty if no user exists with the given ID
     */
    Optional<User> findById(Long id);

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user
     * @return an {@link Optional} containing the found user, or empty if no user exists with the given username
     */
    Optional<User> findByUsername(String username);

    /**
     * Creates a default user if one does not already exist.
     *
     * @return the created default user, or an existing user if one already exists
     */
    User createDefaultUser();
}