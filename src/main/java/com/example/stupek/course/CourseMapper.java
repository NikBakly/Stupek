package com.example.stupek.course;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDto toCourseDto(Course course);

    Course toCourse(CourseDto courseDto);
}
