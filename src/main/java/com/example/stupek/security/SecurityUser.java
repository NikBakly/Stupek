package com.example.stupek.security;

import com.example.stupek.person.Person;
import com.example.stupek.person.PersonStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class SecurityUser implements UserDetails {
    private final String login;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUser(Person person) {
        return new User(
                person.getLogin(),
                person.getPassword(),
                person.getPersonStatus().equals(PersonStatus.ACTIVE),
                person.getPersonStatus().equals(PersonStatus.ACTIVE),
                person.getPersonStatus().equals(PersonStatus.ACTIVE),
                person.getPersonStatus().equals(PersonStatus.ACTIVE),
                person.getPersonRole().getAuthorities()
        );
    }
}
