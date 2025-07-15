package com.wudc.storypool.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class EmailCodeResponse {
    private int seconds;

    public EmailCodeResponse() {
        this.seconds = 180;
    }
}