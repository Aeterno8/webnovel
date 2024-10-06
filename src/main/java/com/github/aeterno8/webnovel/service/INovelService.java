package com.github.aeterno8.webnovel.service;
import com.github.aeterno8.webnovel.DTO.NovelUploadDTO;
import com.github.aeterno8.webnovel.models.Novel;

import java.util.List;
import java.util.Optional;

public interface INovelService {

    Novel uploadNovel(NovelUploadDTO novelUploadDTO);

    List<Novel> findAllNovels();

    Optional<Novel> findById(Long id);

    Novel save(Novel novel);

    void deleteById(Long id);
}