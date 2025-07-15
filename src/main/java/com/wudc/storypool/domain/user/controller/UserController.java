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
    public ResponseEntity<ProfileResponse> getProfile() {
        return ResponseEntity.ok(userService.getProfile());
    }

    @Operation(summary = "프로필 수정")
    @PatchMapping("/me")
    public ResponseEntity<UpdateProfileResponse> updateProfile(@RequestBody UpdateProfileRequest req) {
        return ResponseEntity.ok(userService.updateProfile(req));
    }
}
