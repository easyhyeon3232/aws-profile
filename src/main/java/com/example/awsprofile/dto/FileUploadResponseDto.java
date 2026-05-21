package com.example.awsprofile.dto;

import lombok.Getter;

@Getter
public class FileUploadResponseDto {
    private final String key;

    public FileUploadResponseDto(String key) {
        this.key = key;
    }
}