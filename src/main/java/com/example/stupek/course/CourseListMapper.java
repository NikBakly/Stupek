package com.example.stupek.course;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface CourseListMapper {
    List<CourseDto> toCourseDtoList(List<Course> courses);
}
