package com.example.stupek.person;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface PersonListMapper {
    List<PersonDto> toPersonDtoList(List<Person> persons);

    List<PersonViewDto> toPersonViesDtoList(List<Person> persons);
}
