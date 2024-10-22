package com.github.aeterno8.webnovel.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChapterDTO {

    @JsonProperty("id")
    private int chapterNumber;

    private long novelId;
    private String url;
    private String title;
    private int volume;
    private String volumeTitle;
    private String body;
    private boolean success;
}