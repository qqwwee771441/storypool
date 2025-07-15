package com.wudc.storypool.domain.auth.controller;

import com.wudc.storypool.domain.auth.dto.*;
import com.wudc.storypool.domain.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/validate")
    public ResponseEntity<ValidateResponse> validate() {
        return ResponseEntity.ok(authService.validate());
    }

    @Operation(summary = "이메일 인증 코드 발송")
    @PostMapping("/send-code")
    public ResponseEntity<EmailCodeResponse> sendCode(@RequestBody EmailCodeRequest req) {
        return ResponseEntity.ok(authService.sendEmailCode(req));
    }

    @Operation(summary = "이메일 인증 토큰 발급")
    @PostMapping("/verify-code")
    public ResponseEntity<EmailTokenResponse> verifyToken(@RequestBody EmailTokenRequest req) {
        return ResponseEntity.ok(authService.verifyEmailCode(req));
    }

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signup(@RequestBody SignUpRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(req));
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @Operation(summary = "토큰 갱신")
    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponse> refresh(@RequestBody RefreshRequest req) {
        return ResponseEntity.ok(authService.refresh(req));
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<LogoutResponse> logout(@RequestBody LogoutRequest req) {
        authService.logout(req);
        return ResponseEntity.ok(authService.logout(req));
    }
}
