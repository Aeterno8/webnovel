package com.github.aeterno8.webnovel.DTO;

import com.github.aeterno8.webnovel.validation.ValidZipFile;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@ValidZipFile
@Getter
@Setter
public class NovelUploadDTO {
    @NotNull(message = "File must not be null")
    private MultipartFile file;
}