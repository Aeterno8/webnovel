package com.github.aeterno8.webnovel.service.Impl;

import com.github.aeterno8.webnovel.models.Author;
import com.github.aeterno8.webnovel.models.User;
import com.github.aeterno8.webnovel.repository.AuthorRepository;
import com.github.aeterno8.webnovel.repository.UserRepository;
import com.github.aeterno8.webnovel.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final UserRepository userRepository;

    @Value("${default.user.username}")
    private String defaultUsername;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, UserRepository userRepository) {
        this.authorRepository = authorRepository;
        this.userRepository = userRepository;
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> findAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    public Author findOrCreateAuthorByName(String name) {
        Optional<Author> existingAuthor = authorRepository.findByName(name);

        return existingAuthor.orElseGet(() -> {
            Author newAuthor = new Author();
            newAuthor.setName(name);

            // Since user authentication and authorization are not yet implemented,
            // we will use the default admin user for now.
            User defaultUser = userRepository.findByUsername(defaultUsername)
                    .orElseThrow(() -> new IllegalStateException("Default user not found"));
            newAuthor.setCreatedBy(defaultUser);

            return authorRepository.save(newAuthor);
        });
    }
}