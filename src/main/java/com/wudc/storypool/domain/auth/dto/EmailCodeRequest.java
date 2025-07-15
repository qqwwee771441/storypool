package com.wudc.storypool.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class EmailCodeRequest {
    @Schema(example = "wudc@storypool.com")
    private String email;
}
