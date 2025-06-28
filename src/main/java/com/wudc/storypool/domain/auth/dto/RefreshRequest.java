package com.wudc.storypool.domain.auth.dto;

import lombok.Getter;

@Getter
public class RefreshRequest {
    private String accessToken;
    private String refreshToken;
}