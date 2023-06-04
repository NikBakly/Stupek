package com.example.stupek.person;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum PersonRole {
    USER(Set.of(PersonPermission.USER_READ)),
    ADMIN(Set.of(PersonPermission.USER_READ, PersonPermission.ADMINS)),
    DEVELOPER(Set.of(PersonPermission.USER_READ, PersonPermission.DEVELOPERS_WRITE));

    private final Set<PersonPermission> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
