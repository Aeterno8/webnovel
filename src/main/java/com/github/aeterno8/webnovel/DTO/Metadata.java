package com.github.aeterno8.webnovel.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// json file that is mapped to this class contains a lot of data we are not interested in(we have no control of it), so this annotation is added
// but some limits to file size should be enforced, in case someone maliciously adds more json fields
@JsonIgnoreProperties(ignoreUnknown = true)
public class Metadata {

    private Novel novel;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Novel {
        private String title;
        private List<String> authors;
        private String coverUrl;
        private List<Chapter> chapters;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Chapter {
        private int id;
        private String url;
        private String title;
        private int volume;
        private String volumeTitle;
        private String body;
        private Map<String, Object> images;
        private boolean success;
    }
}