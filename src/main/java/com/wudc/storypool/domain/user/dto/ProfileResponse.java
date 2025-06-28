package com.wudc.storypool.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProfileResponse {
    private final String email;
    private final String nickname;
    private final String profileImageUrl;
    private final String description;

    @Builder
    public ProfileResponse(String email, String nickname, String profileImageUrl, String description) {
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.description = description;
    }
}