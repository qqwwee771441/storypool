package com.wudc.storypool.domain.story.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetStoryResponse {
    private String id;
    private String name;
    private String text;
}
