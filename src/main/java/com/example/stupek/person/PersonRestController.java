package com.example.stupek.person;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/persons")
public class PersonRestController {
    private final PersonService personService;

    @PostMapping
    @PreAuthorize("hasAuthority('users:read')")
    public PersonViewDto registration(@RequestBody PersonDto personDto) {
        return personService.create(personDto);
    }

    @PatchMapping("/{personId}")
    @PreAuthorize("hasAuthority('users:read')")
    public PersonViewDto updateById(@PathVariable Long personId, @RequestBody PersonDto personDto) {
        return personService.updateById(personId, personDto);
    }

    @GetMapping("/{personId}")
    @PreAuthorize("hasAuthority('users:read')")
    public PersonViewDto findPersonById(@PathVariable Long personId) {
        return personService.findById(personId);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('users:read')")
    public List<PersonViewDto> findAll(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                       @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
        return personService.findAll(offset, limit);
    }

    @DeleteMapping("/{personId}")
    @PreAuthorize("hasAuthority('admins:write')")
    public void deletePersonById(@PathVariable Long personId) {
        personService.deleteById(personId);
    }
}
