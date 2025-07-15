package com.wudc.storypool.domain.story.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetStoryListResponse {
    private List<StorySummary> stories;
    private boolean hasNext;
    private String nextCursor;
}
