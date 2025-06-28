package com.wudc.storypool.domain.auth.dto;

import lombok.Getter;

@Getter
public class WithdrawRequest {
    private String accessToken;
    private String refreshToken;
    private String password;
}