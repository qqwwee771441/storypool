package com.wudc.storypool.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class UpdateProfileRequest {
    @Schema(example = "UpdatedNickname")
    private String nickname;
    @Schema(example = "UpdatedProfileImageUrl")
    private String profileImageUrl;
    @Schema(example = "UpdatedDescription")
    private String description;
}
