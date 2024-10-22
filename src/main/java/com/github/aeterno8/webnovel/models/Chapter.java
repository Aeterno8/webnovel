package com.github.aeterno8.webnovel.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Transient;

@Data
@Document(collection = "chapters")
public class Chapter {

    @Transient
    public static final String SEQUENCE_NAME = "chapters_sequence";

    @Id
    private long id;

    private long novelId;

    private int chapterNumber;
    private String url;
    private String title;
    private int volume;
    private String volumeTitle;
    private String body;
    private boolean success;
}