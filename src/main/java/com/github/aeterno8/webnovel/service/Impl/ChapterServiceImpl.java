package com.github.aeterno8.webnovel.service.Impl;

import com.github.aeterno8.webnovel.models.Chapter;
import com.github.aeterno8.webnovel.repository.ChapterRepository;
import com.github.aeterno8.webnovel.service.ChapterService;
import com.github.aeterno8.webnovel.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Autowired // is not needed(done implicitly), but I like using it because it makes this action explicit
    public ChapterServiceImpl(ChapterRepository chapterRepository, SequenceGeneratorService sequenceGeneratorService) {
        this.chapterRepository = chapterRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public Chapter saveChapter(Chapter chapter) {
        chapter.setId(sequenceGeneratorService.generateSequence(Chapter.SEQUENCE_NAME));
        return chapterRepository.save(chapter);
    }

    @Override
    public Chapter getChapterById(long id) {
        Optional<Chapter> chapter = chapterRepository.findById(id);
        return chapter.orElse(null);
    }

    @Override
    public List<Chapter> getAllChapters() {
        return chapterRepository.findAll();
    }

    @Override
    public List<Chapter> getChaptersByNovelId(long novelId) {
        return chapterRepository.findByNovelId(novelId);
    }

    @Override
    public void deleteChapter(long id) {
        chapterRepository.deleteById(id);
    }

    @Override
    public Chapter getChapterByNovelIdAndChapterId(long novelId, long chapterId) {
        return chapterRepository.findByNovelIdAndChapterNumber(novelId, chapterId);
    }
}