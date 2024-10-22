package com.github.aeterno8.webnovel.service;


import com.github.aeterno8.webnovel.models.Chapter;

import java.util.List;

public interface ChapterService {
    /**
     * Saves a new chapter.
     *
     * @param chapter the chapter to save
     * @return the saved chapter
     */
    Chapter saveChapter(Chapter chapter);

    /**
     * Retrieves a chapter by its unique ID.
     *
     * @param id the unique ID of the chapter
     * @return the chapter with the specified ID, or null if not found
     */
    Chapter getChapterById(long id);

    /**
     * Retrieves all chapters.
     *
     * @return a list of all chapters
     */
    List<Chapter> getAllChapters();

    /**
     * Retrieves chapters associated with a specific novel ID.
     *
     * @param novelId the unique ID of the novel
     * @return a list of chapters belonging to the specified novel
     */
    List<Chapter> getChaptersByNovelId(long novelId);

    /**
     * Retrieves a single chapter by its novel ID and chapter ID.
     *
     * @param novelId the unique ID of the novel
     * @param chapterId the unique ID of the chapter (chapter number)
     * @return the chapter matching the specified novel and chapter IDs, or null if not found
     */
    Chapter getChapterByNovelIdAndChapterId(long novelId, long chapterId);

    /**
     * Deletes a chapter by its unique ID.
     *
     * @param id the unique ID of the chapter to delete
     */
    void deleteChapter(long id);
}