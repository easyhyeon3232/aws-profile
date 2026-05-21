package com.example.awsprofile.service;

import com.example.awsprofile.entity.Member;
import com.example.awsprofile.repository.MembersRepository;
import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {


    private static final Duration PRESIGNED_URL_EXPIRATION = Duration.ofDays(7);

    private final S3Template s3Template;
    private final MembersRepository membersRepository;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @Transactional
    public String upload(Long id, MultipartFile file) {
        Member member = membersRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 멤버입니다.")
        );

        try {
            String key = "uploads/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
            s3Template.upload(bucket, key, file.getInputStream());

            member.updateProfileImageKey(key);
            return key;

        } catch (IOException e) {
            // 적절한 커스텀 예외로 바꾸고, GlobalExceptionHandler로 핸들링 필요
            throw new RuntimeException("파일 업로드 실패", e);
        }
    }

    @Transactional
    public URL getDownloadUrl(Long id) {
        Member member = membersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. id=" + id));

        String key = member.getProfileImageKey();
        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("프로필 이미지가 존재하지 않습니다. id=" + id);
        }

        return s3Template.createSignedGetURL(bucket, key, PRESIGNED_URL_EXPIRATION);
    }
}
