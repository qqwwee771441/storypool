package com.wudc.storypool.domain.story.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class CreateStoryRequest {
    @Schema(example = "CreatedStoryName")
    private String name;
    @Schema(example = "CreatedStoryText")
    private String text;
}
