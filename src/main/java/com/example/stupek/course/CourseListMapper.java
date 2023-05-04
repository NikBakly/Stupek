package com.example.stupek.course;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface CourseListMapper {
    List<CourseDto> toCourseDtoList(List<Course> courses);
}
