package com.example.stupek.person;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import java.util.List;

public interface PersonService {
    PersonViewDto create(@Valid PersonDto personDto);

    PersonViewDto updateById(@Min(1) Long personId, @Valid PersonDto updatedPerson);

    PersonViewDto findById(@Min(1) Long personId);

    List<PersonViewDto> findAll(@Min(0) Integer offset, @Min(1) Integer limit);

    void deleteById(@Min(1) Long personId);

}
