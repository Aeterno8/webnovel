package com.github.aeterno8.webnovel.repository;

import com.github.aeterno8.webnovel.models.Novel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovelRepository extends JpaRepository<Novel, Long> {
}
