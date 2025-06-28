package com.wudc.storypool.domain.user.dto;

import lombok.Getter;

@Getter
public class ChangePasswordRequest {
    private String password;
    private String newPassword;
}