package com.github.aeterno8.webnovel.service;

import com.github.aeterno8.webnovel.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAllAuthors();

    Optional<Author> findAuthorById(Long id);

    Author saveAuthor(Author author);

    void deleteAuthorById(Long id);
}