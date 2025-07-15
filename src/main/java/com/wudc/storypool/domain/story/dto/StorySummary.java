package com.wudc.storypool.domain.story.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StorySummary {
    private String id;
    private String name;
    private String excerpt;
    private String createdAt;
    private String updatedAt;
}
