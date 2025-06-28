package com.wudc.storypool.global.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final String principal;

    public JwtAuthenticationToken(String userId) {
        super(List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.principal = userId;
        setAuthenticated(true);
    }

    @Override public Object getCredentials() { return null; }
    @Override public Object getPrincipal() { return principal; }
}