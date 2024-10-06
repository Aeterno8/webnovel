package com.github.aeterno8.webnovel.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ZipFileValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidZipFile {

    String message() default "uploaded file must be a valid ZIP file";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}