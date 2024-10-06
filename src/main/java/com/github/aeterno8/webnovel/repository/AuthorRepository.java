package com.github.aeterno8.webnovel.repository;

import com.github.aeterno8.webnovel.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
