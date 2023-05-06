package com.example.stupek.person;

import java.util.List;

public interface PersonService {
    PersonViewDto create(PersonDto personDto);

    PersonViewDto updateById(Long personId, PersonDto updatedPerson);

    PersonViewDto findById(Long personId);

    List<PersonViewDto> findAll(Integer offset, Integer limit);

    void deleteById(Long personId);

}
