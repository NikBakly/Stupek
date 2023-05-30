package com.example.stupek.person;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/persons")
public class PersonRestController {
    private final PersonService personService;

    @PostMapping
    public PersonViewDto create(@RequestBody PersonDto personDto) {
        return personService.create(personDto);
    }

    @PatchMapping("/{personId}")
    @PreAuthorize("hasAuthority('users:read')")
    public PersonViewDto updateByLogin(@PathVariable Long personId,
                                       @RequestBody PersonDtoForUpdate updatedPerson) {
        return personService.updateById(personId, updatedPerson);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('users:read')")
    public PersonViewDto findPersonByLogin(@AuthenticationPrincipal User user) {
        return personService.findByLogin(user.getUsername());
    }

    @GetMapping("/all")
    public List<PersonViewDto> findAll(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                       @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return personService.findAll(offset, limit);
    }

    @DeleteMapping("/{personId}")
    @PreAuthorize("hasAuthority('admins:write')")
    public void deletePersonByLogin(@PathVariable Long personId) {
        personService.deleteById(personId);
    }
}
