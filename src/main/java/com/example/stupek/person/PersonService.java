package com.example.stupek.person;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public interface PersonService {
    PersonViewDto create(@Valid PersonDto personDto);

    PersonViewDto updateById(@Min(1) Long personId, @Valid PersonDtoForUpdate updatedPerson);

    PersonViewDto findByLogin(@NotBlank String personLogin);

    List<PersonViewDto> findAll(@Min(0) Integer offset, @Min(1) Integer limit);

    void deleteById(@Min(1) Long personId);

}
