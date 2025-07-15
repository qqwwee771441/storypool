package com.wudc.storypool.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UpdateProfileResponse {
    private String nickname;
    private String profileImageUrl;
    private String description;
}
