package com.example.stupek.security;

import com.example.stupek.person.Person;
import com.example.stupek.person.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Person foundPerson = personRepository.findPersonByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Person doesn't exists"));
        return SecurityUser.fromUser(foundPerson);
    }
}
