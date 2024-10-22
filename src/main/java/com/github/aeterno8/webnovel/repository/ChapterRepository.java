package com.github.aeterno8.webnovel.repository;

import com.github.aeterno8.webnovel.models.Chapter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends MongoRepository<Chapter, Long> {
    List<Chapter> findByNovelId(long novelId);
    Chapter findByNovelIdAndChapterNumber(long novelId, long chapterNumber);
}