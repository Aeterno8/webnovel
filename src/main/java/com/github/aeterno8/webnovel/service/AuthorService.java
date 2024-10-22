package com.github.aeterno8.webnovel.service;

import com.github.aeterno8.webnovel.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    /**
     * Finds all authors in the repository.
     *
     * @return A list of all authors.
     */
    List<Author> findAllAuthors();

    /**
     * Finds an author by their unique ID.
     *
     * @param id The unique identifier of the author.
     * @return An Optional containing the found author, or empty if not found.
     */
    Optional<Author> findAuthorById(Long id);

    /**
     * Saves the given author to the repository.
     *
     * @param author The author to save.
     * @return The saved author.
     */
    Author saveAuthor(Author author);

    /**
     * Deletes an author by their unique ID.
     *
     * @param id The unique identifier of the author to delete.
     */
    void deleteAuthorById(Long id);

    /**
     * Finds an author by name or creates a new one if it does not exist.
     *
     * @param name The name of the author.
     * @return The existing or newly created author.
     */
    Author findOrCreateAuthorByName(String name);
}