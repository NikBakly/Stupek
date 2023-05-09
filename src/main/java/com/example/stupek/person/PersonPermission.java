package com.example.stupek.person;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PersonPermission {
    USER_READ("users:read"),
    DEVELOPERS_WRITE("developers:write"),
    ADMINS("admins:write");

    private final String permission;
}
