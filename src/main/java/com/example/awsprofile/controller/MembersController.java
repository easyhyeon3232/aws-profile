package com.example.awsprofile.controller;

import com.example.awsprofile.dto.*;
import com.example.awsprofile.service.MembersService;
import com.example.awsprofile.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Slf4j
public class MembersController {

    private final MembersService membersService;
    private final S3Service s3Service;


    @PostMapping
    public ResponseEntity<CreateResponseDto> create(@RequestBody createRequestDto requestDto) {
        log.info("[API - LOG] POST/api/members");
        return ResponseEntity.ok(membersService.save(requestDto));

    }

    @PostMapping("/{id}/profile-image")
    public ResponseEntity<FileUploadResponseDto> upload(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        String key = s3Service.upload(id, file);
        return ResponseEntity.ok(new FileUploadResponseDto(key));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetResponseDto> getMembers(@PathVariable Long id) {
        log.info("[API - LOG] GET/api/members/{id}");
        return ResponseEntity.ok(membersService.getMembers(id));
    }

    @GetMapping("/{id}/profile-image")
    public ResponseEntity<FileDownloadUrlResponseDto> getDownloadUrl(@PathVariable Long id) {
        URL url = s3Service.getDownloadUrl(id);
        return ResponseEntity.ok(new FileDownloadUrlResponseDto(url.toString()));
    }

    @GetMapping("/deploy-check")
    public String deployCheck() {
        return "deploy-check-v2";
    }
}
