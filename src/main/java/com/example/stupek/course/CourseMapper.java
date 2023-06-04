package com.example.stupek.course;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {Float.class, LocalDateTime.class})
public interface CourseMapper {

    CourseDto toCourseDto(Course course);

    @Mapping(target = "isOpen", defaultValue = "true")
    @Mapping(target = "lastUpdate", expression = "java(LocalDateTime.now())")
    Course toCourse(CourseDto courseDto);
}
