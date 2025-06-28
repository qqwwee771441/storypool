package com.wudc.storypool.domain.user.dto;

import lombok.Getter;

@Getter
public class UpdateProfileRequest {
    private String newNickname;
    private String newDescription;
}