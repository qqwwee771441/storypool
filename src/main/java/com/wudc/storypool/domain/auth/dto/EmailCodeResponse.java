package com.wudc.storypool.domain.auth.dto;

import lombok.Getter;

@Getter
public class EmailCodeResponse {
    private int seconds;

    public EmailCodeResponse() {
        this.seconds = 180;
    }
}