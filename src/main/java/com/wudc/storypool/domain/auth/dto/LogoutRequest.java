package com.wudc.storypool.domain.auth.dto;

import lombok.Getter;

@Getter
public class LogoutRequest {
    private String accessToken;
    private String refreshToken;
}