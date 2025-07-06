package com.wudc.storypool.domain.user.controller;

import com.wudc.storypool.domain.user.dto.*;
import com.wudc.storypool.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "프로필 조회")
    @GetMapping("/me")
    public ResponseEntity<ProfileResponse> getProfile(@RequestParam String userId) {
        return ResponseEntity.ok(userService.getProfile(userId));
    }

    @Operation(summary = "프로필 수정")
    @PatchMapping("/me")
    public ResponseEntity<ProfileResponse> updateProfile(@RequestParam String userId,
                                                         @RequestBody UpdateProfileRequest req) {
        return ResponseEntity.ok(userService.updateProfile(userId, req));
    }

    @Operation(summary = "비밀번호 변경")
    @PatchMapping("/me/password")
    public ResponseEntity<Boolean> changePassword(@RequestParam String userId,
                                                  @RequestBody ChangePasswordRequest req) {
        return ResponseEntity.ok(userService.changePassword(userId, req));
    }

    @Operation(summary = "Presign URL 발급")
    @GetMapping("/me/presign")
    public ResponseEntity<PresignResponse> getPresignUrl() {
        return ResponseEntity.ok(userService.getPresignUrl());
    }

    @Operation(summary = "이미지 업로드 시뮬레이션")
    @PostMapping("/me/profile-image")
    public ResponseEntity<String> uploadImage(@RequestParam String userId,
                                              @RequestParam MultipartFile file) {
        String url = userService.uploadImage(userId, file);
        return ResponseEntity.ok(url);
    }

    @Operation(summary = "프로필 이미지 URL 저장")
    @PatchMapping("/me/profile-image")
    public ResponseEntity<String> confirmImage(
            @RequestParam String userId,
            @RequestBody Map<String, String> body) {
        String newUrl = body.get("newProfileImageUrl");
        return ResponseEntity.ok(userService.confirmImage(userId, newUrl));
    }


}
