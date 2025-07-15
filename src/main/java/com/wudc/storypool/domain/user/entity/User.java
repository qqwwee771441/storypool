package com.wudc.storypool.domain.user.entity;

import com.wudc.storypool.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name="users")
public class User extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Setter
    private String nickname;

    @Setter
    private String profileImageUrl;

    @Setter
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public enum Role { USER, ADMIN }

    public void updateProfile(String nickname, String profileImageUrl, String description) {
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.description = description;
    }
}
