package com.example.stupek.person;

import com.example.stupek.course.CourseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.CharBuffer;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        uses = CourseMapper.class,
        imports = {LocalDateTime.class, CharBuffer.class})
public abstract class PersonMapper {
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "personRole", defaultValue = "USER")
    @Mapping(target = "personStatus", defaultValue = "ACTIVE")
    @Mapping(target = "balance", defaultExpression = "java(Float.valueOf(0))")
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(CharBuffer.wrap(personDto.getPassword())))")
    @Mapping(target = "lastUpdate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "id", ignore = true)
    abstract Person toPerson(PersonDto personDto);

    abstract PersonViewDto toPersonViewDto(Person person);
}
