package com.github.aeterno8.webnovel.service.Impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.aeterno8.webnovel.DTO.ChapterDTO;
import com.github.aeterno8.webnovel.DTO.Metadata;
import com.github.aeterno8.webnovel.DTO.NovelUploadDTO;
import com.github.aeterno8.webnovel.mapper.ChapterMapper;
import com.github.aeterno8.webnovel.models.Author;
import com.github.aeterno8.webnovel.models.Chapter;
import com.github.aeterno8.webnovel.models.Novel;
import com.github.aeterno8.webnovel.models.User;
import com.github.aeterno8.webnovel.repository.NovelRepository;
import com.github.aeterno8.webnovel.service.AuthorService;
import com.github.aeterno8.webnovel.service.ChapterService;
import com.github.aeterno8.webnovel.service.NovelService;
import com.github.aeterno8.webnovel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class NovelServiceImpl implements NovelService {

    private final NovelRepository novelRepository;

    private final AuthorService authorService;

    private final ChapterService chapterService;

    private final UserService userService;

    private final ChapterMapper chapterMapper;

    private static final Pattern CHAPTER_FILE_PATTERN = Pattern.compile("\\d{5}\\.json$");

    @Autowired
    public NovelServiceImpl(NovelRepository novelRepository, AuthorService authorService, ChapterService chapterService, UserService userService, ChapterMapper chapterMapper) {
        this.novelRepository = novelRepository;
        this.authorService = authorService;
        this.chapterService = chapterService;
        this.userService = userService;
        this.chapterMapper = chapterMapper;
    }

    // The current implementation is not optimal. A better approach would be using a message broker
    // to queue the novels for processing by separate worker servers. This way, when a novel is placed
    // on the queue, we can immediately return a success status code to the user, this gives better user
    // experience. Also, we could implement further validation to ensure that all required
    // files are present, so that risk of issues during processing is minimal and that there is eventual
    // consistency.
    // This more optimal approach will be taken into consideration once application reaches point of MVB (minimal viable product)
    public Novel uploadNovel(NovelUploadDTO novelUploadDTO) throws IOException {
        MultipartFile file = novelUploadDTO.getFile();
        Novel novel = new Novel();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String authorName = "Unknown"; // fallback, if no author name is mapped

        // We use a default admin user for the created_by field since authentication is not implemented yet
        User defaultUser = userService.createDefaultUser();
        novel.setCreatedBy(defaultUser);

        try (ZipInputStream zis = new ZipInputStream(file.getInputStream())) {
            ZipEntry zipEntry;

            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().endsWith("meta.json")) {
                    // Save to ByteArrayOutputStream to prevent closing the stream
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = zis.read(buffer)) != -1) {
                        baos.write(buffer, 0, bytesRead);
                    }

                    Metadata metadata = objectMapper.readValue(baos.toByteArray(), Metadata.class);
                    novel.setTitle(metadata.getNovel().getTitle());
                    novel.setDescription("EMPTY"); // current inputs do not have description

                    List<String> authors = metadata.getNovel().getAuthors();
                    if (authors != null && !authors.isEmpty()) {
                        authorName = authors.get(0);
                    }

                    Author author = authorService.findOrCreateAuthorByName(authorName);
                    novel.setAuthor(author);
                    novel = novelRepository.save(novel);
                } else if (CHAPTER_FILE_PATTERN.matcher(zipEntry.getName()).find()) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = zis.read(buffer)) != -1) {
                        baos.write(buffer, 0, bytesRead);
                    }

                    ChapterDTO chapterDTO = objectMapper.readValue(baos.toByteArray(), ChapterDTO.class);
                    Chapter chapter = chapterMapper.toEntity(chapterDTO);
                    chapter.setNovelId(novel.getId());
                    chapterService.saveChapter(chapter);
                }

                zis.closeEntry();
            }
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