package com.wudc.storypool.domain.story.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class UpdateStoryRequest {
    @Schema(example = "UpdatedStoryName")
    private String name;
    @Schema(example = "UpdatedStoryText")
    private String text;
}
