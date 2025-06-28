package com.wudc.storypool.domain.user.service;

import com.wudc.storypool.domain.user.dto.*;
import com.wudc.storypool.domain.user.entity.User;
import com.wudc.storypool.domain.user.repository.UserRepository;
import de.huxhorn.sulky.ulid.ULID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final ULID ulid = new ULID();

    public ProfileResponse getProfile(String userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return ProfileResponse.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .description(user.getDescription())
                .build();
    }

    @Transactional
    public ProfileResponse updateProfile(String userId, UpdateProfileRequest req) {
        User user = userRepository.findById(userId).orElseThrow();
        user.updateProfile(req.getNewNickname(), req.getNewDescription());
        return ProfileResponse.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .description(user.getDescription())
                .build();
    }

    @Transactional
    public boolean changePassword(String userId, ChangePasswordRequest req) {
        User user = userRepository.findById(userId).orElseThrow();
        if (!encoder.matches(req.getPassword(), user.getPassword())) return false;
        user.changePassword(encoder.encode(req.getNewPassword()));
        return true;
    }

    public PresignResponse getPresignUrl() {
        String key = ulid.nextULID() + ".png";
        String url = "http://localhost:8080/images/" + key;
        return new PresignResponse(url);
    }

    @Transactional
    public String uploadImage(String userId, MultipartFile file) {
        // simulate upload: generate URL
        String key = ulid.nextULID() + ".png";
        String url = "http://localhost:8080/images/" + key;
        return url;
    }

    @Transactional
    public String confirmImage(String userId, String newProfileImageUrl) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setProfileImageUrl(newProfileImageUrl);
        return user.getProfileImageUrl();
    }
}