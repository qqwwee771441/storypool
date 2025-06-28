package com.wudc.storypool.domain.auth.service;

import com.wudc.storypool.domain.auth.dto.*;
import com.wudc.storypool.domain.user.entity.User;
import com.wudc.storypool.domain.user.repository.UserRepository;
import de.huxhorn.sulky.ulid.ULID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final ULID ulid = new ULID();

    // in-memory stores
    private final Map<String,String> emailTokens = new ConcurrentHashMap<>();
    private final Map<String,String> refreshStore = new ConcurrentHashMap<>();

    public EmailCodeResponse sendEmailCode(EmailCodeRequest req) {
        // ignore actual send, return fixed TTL
        return new EmailCodeResponse();
    }

    public EmailTokenResponse verifyEmailCode(EmailTokenRequest req) {
        // accept any code, generate token
        String token = ulid.nextULID();
        emailTokens.put(req.getEmail(), token);
        return new EmailTokenResponse(token);
    }

    @Transactional
    public SignUpResponse signup(SignUpRequest req) {
        String savedToken = emailTokens.get(req.getEmail());
        if (savedToken == null || !savedToken.equals(req.getEmailToken())) {
            return new SignUpResponse(false);
        }
        User user = User.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .nickname("")
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
        String access = ulid.nextULID();
        String refresh = ulid.nextULID();
        refreshStore.put(refresh, user.getId());
        return new LoginResponse(access, refresh);
    }

    public RefreshResponse refresh(RefreshRequest req) {
        if (!refreshStore.containsKey(req.getRefreshToken())) {
            throw new IllegalArgumentException("Invalid refresh token");
        }
        String newAccess = ulid.nextULID();
        String newRefresh = ulid.nextULID();
        String userId = refreshStore.remove(req.getRefreshToken());
        refreshStore.put(newRefresh, userId);
        return new RefreshResponse(newAccess, newRefresh);
    }

    public void logout(LogoutRequest req) {
        refreshStore.remove(req.getRefreshToken());
    }

    @Transactional
    public void withdraw(WithdrawRequest req) {
        // simple removal
        User user = userRepository.findById(req.getAccessToken())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        userRepository.delete(user);
        refreshStore.remove(req.getRefreshToken());
    }
}