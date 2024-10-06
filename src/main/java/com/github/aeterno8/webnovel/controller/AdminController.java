package com.github.aeterno8.webnovel.controller;

import com.github.aeterno8.webnovel.DTO.NovelUploadDTO;
import com.github.aeterno8.webnovel.service.INovelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@Validated
public class AdminController {

    private final INovelService novelService;

    @Autowired
    public AdminController(INovelService novelService) {
        this.novelService = novelService;
    }

    @PostMapping("/upload-novel")
    public ResponseEntity<String> uploadNovel(@Valid @ModelAttribute NovelUploadDTO novelUploadDTO) {
        novelService.uploadNovel(novelUploadDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

}