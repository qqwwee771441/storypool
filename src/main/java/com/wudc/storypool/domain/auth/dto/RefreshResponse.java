package com.wudc.storypool.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RefreshResponse {
    private String newAccessToken;
    private String newRefreshToken;
}