package com.wudc.storypool.domain.user.service;

import com.wudc.storypool.domain.auth.service.AuthService;
import com.wudc.storypool.domain.user.dto.*;
import com.wudc.storypool.domain.user.entity.User;
import com.wudc.storypool.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final AuthService authService;
    private final UserRepository userRepository;

    public ProfileResponse getProfile() {
        User user = userRepository.findById(authService.getUserId()).orElseThrow();
        return ProfileResponse.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .description(user.getDescription())
                .build();
    }

    public UpdateProfileResponse updateProfile(UpdateProfileRequest req) {
        User user = userRepository.findById(authService.getUserId()).orElseThrow();
        user.updateProfile(req.getNickname(), req.getProfileImageUrl(), req.getDescription());
        return UpdateProfileResponse.builder()
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .description(user.getDescription())
                .build();
    }
}
