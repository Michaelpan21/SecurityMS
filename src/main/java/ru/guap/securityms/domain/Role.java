package ru.guap.securityms.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    STUDENT,
    PROFESSOR,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
