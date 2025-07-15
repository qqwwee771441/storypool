package com.wudc.storypool.domain.auth.service;

import com.wudc.storypool.domain.auth.dto.*;
import com.wudc.storypool.domain.user.entity.User;
import com.wudc.storypool.domain.user.repository.UserRepository;
import de.huxhorn.sulky.ulid.ULID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final ULID ulid = new ULID();

    // in-memory stores
    private final Map<String,String> emailTokenStore = new ConcurrentHashMap<>();
    private final Map<String,String> accessTokenStore = new ConcurrentHashMap<>();
    private final Map<String,String> refreshTokenStore = new ConcurrentHashMap<>();

    public ValidateResponse validate() {
        return new ValidateResponse("유효한 토큰입니다.", getUserId());
    }

    public EmailCodeResponse sendEmailCode(EmailCodeRequest req) {
        // ignore actual send, return fixed TTL
        return new EmailCodeResponse();
    }

    public EmailTokenResponse verifyEmailCode(EmailTokenRequest req) {
        // accept any code, generate token
        String token = ulid.nextULID();
        emailTokenStore.put(req.getEmail(), token);
        return new EmailTokenResponse(token);
    }

    public SignUpResponse signup(SignUpRequest req) {
        String savedToken = emailTokenStore.remove(req.getEmail());
        if (savedToken == null || !savedToken.equals(req.getEmailToken())) {
            return new SignUpResponse(false);
        }
        User user = User.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .nickname(req.getEmail().split("@")[0])
                .profileImageUrl("")
                .description("")
                .role(User.Role.USER)
                .build();
        userRepository.save(user);
        return new SignUpResponse(true);
    }

    public LoginResponse login(LoginRequest req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        String accessToken = ulid.nextULID();
        String refreshToken = ulid.nextULID();
        accessTokenStore.put(accessToken, user.getId());
        refreshTokenStore.put(refreshToken, user.getId());
        return new LoginResponse(accessToken, refreshToken);
    }

    public RefreshResponse refresh(RefreshRequest req) {
        if (!refreshTokenStore.containsKey(req.getRefreshToken())) {
            throw new IllegalArgumentException("Invalid refresh token");
        }
        String newAccessToken = ulid.nextULID();
        String newRefreshToken = ulid.nextULID();
        accessTokenStore.put(newAccessToken, getUserId());
        refreshTokenStore.put(newRefreshToken, getUserId());
        accessTokenStore.remove(req.getAccessToken());
        refreshTokenStore.remove(req.getRefreshToken());
        return new RefreshResponse(newAccessToken, newRefreshToken);
    }

    public LogoutResponse logout(LogoutRequest req) {
        accessTokenStore.remove(req.getAccessToken());
        refreshTokenStore.remove(req.getRefreshToken());
        return new LogoutResponse("로그아웃되었습니다.");
    }

    public String getUserId() {
        String accessToken = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return accessTokenStore.get(accessToken);
    }
}
