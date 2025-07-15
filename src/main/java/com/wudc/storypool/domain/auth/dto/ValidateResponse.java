package com.wudc.storypool.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidateResponse {
    private String message;
    private String userId;
}
