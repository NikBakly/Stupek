package com.example.stupek.person;

import com.example.stupek.course.CourseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CourseMapper.class)
public interface PersonMapper {
    PersonDto toPersonDto(Person person);

    @Mapping(target = "personRole", defaultValue = "USER")
    @Mapping(target = "personStatus", defaultValue = "ACTIVE")
    Person toPerson(PersonDto personDto);

    PersonViewDto toPersonViewDto(Person person);
}
