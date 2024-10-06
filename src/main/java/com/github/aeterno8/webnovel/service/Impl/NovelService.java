package com.github.aeterno8.webnovel.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.aeterno8.webnovel.DTO.Metadata;
import com.github.aeterno8.webnovel.DTO.NovelUploadDTO;
import com.github.aeterno8.webnovel.models.Novel;
import com.github.aeterno8.webnovel.repository.NovelRepository;
import com.github.aeterno8.webnovel.service.INovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class NovelService implements INovelService {
    private final NovelRepository novelRepository;
    private final AuthorService authorService;

    @Autowired
    public NovelService(NovelRepository novelRepository, AuthorService authorService) {
        this.novelRepository = novelRepository;
        this.authorService = authorService;
    }


    // TODO - not finished
    public Novel uploadNovel(NovelUploadDTO novelUploadDTO) {
        MultipartFile file = novelUploadDTO.getFile();
        Novel novel = new Novel();
        ObjectMapper objectMapper = new ObjectMapper();

        try (ZipInputStream zis = new ZipInputStream(file.getInputStream())) {
            ZipEntry zipEntry;

            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().endsWith("meta.json")) {
                    // we must first save file to ByteArrayOutputStream because if we were to use
                    // ZipInputStream zis directly in readValue method of ObjectMapper, it would close stream and
                    // we would get stream closed exception over next iteration of loop
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = zis.read(buffer)) != -1) {
                        baos.write(buffer, 0, bytesRead);
                    }

                    Metadata metadata = objectMapper.readValue(baos.toByteArray(), Metadata.class);
                    novel.setTitle(metadata.getNovel().getTitle());
                }

                zis.closeEntry();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to process ZIP file: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while processing the ZIP file.", e);
        }

        return novel;
    }

    public List<Novel> findAllNovels() {
        return novelRepository.findAll();
    }

    public Optional<Novel> findById(Long id) {
        return novelRepository.findById(id);
    }

    public Novel save(Novel novel) {
        return novelRepository.save(novel);
    }

    public void deleteById(Long id) {
        novelRepository.deleteById(id);
    }
}