package com.example.stupek.person;

import com.example.stupek.course.CourseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface PersonMapper {
    PersonDto toPersonDto(Person person);

    Person toPerson(PersonDto personDto);

    PersonViewDto toPersonViewDto(Person person);
}
