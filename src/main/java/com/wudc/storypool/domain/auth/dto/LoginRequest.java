package com.wudc.storypool.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class LoginRequest {
    @Schema(example = "wudc@storypool.com")
    private String email;
    @Schema(example = "pusan2015!")
    private String password;
}