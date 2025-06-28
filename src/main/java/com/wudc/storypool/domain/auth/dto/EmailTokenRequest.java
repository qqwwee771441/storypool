package com.wudc.storypool.domain.auth.dto;

import lombok.Getter;

@Getter
public class EmailTokenRequest {
    private String email;
    private String code;
}