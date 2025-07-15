package com.wudc.storypool.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class RefreshRequest {
    @Schema(example = "")
    private String accessToken;
    @Schema(example = "")
    private String refreshToken;
}