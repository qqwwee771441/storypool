package com.wudc.storypool.domain.auth.dto;

import lombok.Getter;

@Getter
public class SignUpRequest {
    private String email;
    private String emailToken;
    private String password;
}