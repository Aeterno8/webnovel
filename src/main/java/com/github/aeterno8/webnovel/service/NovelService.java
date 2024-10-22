package com.github.aeterno8.webnovel.service;
import com.github.aeterno8.webnovel.DTO.NovelUploadDTO;
import com.github.aeterno8.webnovel.models.Novel;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface NovelService {

    /**
     * Uploads a novel based on the provided {@link NovelUploadDTO}.
     *
     * @param novelUploadDTO the data transfer object containing novel upload information
     * @return the uploaded {@link Novel} entity
     * @throws IOException if an I/O error occurs during the upload process
     */
    Novel uploadNovel(NovelUploadDTO novelUploadDTO) throws IOException;

    /**
     * Retrieves all novels.
     *
     * @return a list of all {@link Novel} entities
     */
    List<Novel> findAllNovels();

    /**
     * Finds a novel by its unique identifier.
     *
     * @param id the unique identifier of the novel
     * @return an {@link Optional} containing the found {@link Novel}, or an empty {@link Optional} if no novel is found
     */
    Optional<Novel> findById(Long id);

    /**
     * Saves the provided novel entity.
     *
     * @param novel the {@link Novel} entity to be saved
     * @return the saved {@link Novel} entity
     */
    Novel save(Novel novel);

    /**
     * Deletes a novel by its unique identifier.
     *
     * @param id the unique identifier of the novel to be deleted
     */
    void deleteById(Long id);
}