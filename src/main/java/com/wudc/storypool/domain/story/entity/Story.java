package com.wudc.storypool.domain.story.entity;

import com.wudc.storypool.common.entity.BaseEntity;
import com.wudc.storypool.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name="stories")
public class Story extends BaseEntity {
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String text;

    public void update(String name, String text) {
        this.name = name;
        this.text = text;
    }
}
