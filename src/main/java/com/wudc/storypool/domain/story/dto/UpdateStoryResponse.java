package com.wudc.storypool.domain.story.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateStoryResponse {
    private String draftId;
    private String updatedAt;
}
