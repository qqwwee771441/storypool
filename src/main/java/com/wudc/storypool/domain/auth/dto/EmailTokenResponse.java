package com.wudc.storypool.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailTokenResponse {
    private String emailToken;
}