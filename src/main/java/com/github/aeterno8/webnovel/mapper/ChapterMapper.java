package com.github.aeterno8.webnovel.mapper;

import com.github.aeterno8.webnovel.DTO.ChapterDTO;
import com.github.aeterno8.webnovel.models.Chapter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChapterMapper {
    Chapter toEntity(ChapterDTO chapterDTO);
    ChapterDTO toDTO(Chapter chapter);
}