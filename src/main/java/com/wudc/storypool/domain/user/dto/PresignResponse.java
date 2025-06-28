package com.wudc.storypool.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PresignResponse {
    private String uploadUrl;
}