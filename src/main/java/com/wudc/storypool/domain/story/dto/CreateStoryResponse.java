package com.wudc.storypool.domain.story.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateStoryResponse {
    private String draftId;
    private String createdAt;
}
