package com.github.aeterno8.webnovel.validation;


import com.github.aeterno8.webnovel.DTO.NovelUploadDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ZipFileValidator implements ConstraintValidator<ValidZipFile, NovelUploadDTO> {

    @Override
    public boolean isValid(NovelUploadDTO novelUploadDTO, ConstraintValidatorContext context) {
        if (novelUploadDTO.getFile() == null || novelUploadDTO.getFile().isEmpty()) {
            return false;
        }

        String fileName = novelUploadDTO.getFile().getOriginalFilename();
        return fileName != null && fileName.toLowerCase().endsWith(".zip");
    }
}