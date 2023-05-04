package com.example.stupek.course;

import java.util.List;

public interface CourseService {
    void save(CourseDto courseDto);

    void updateById(Long courseId, CourseDto updatedCourse);

    CourseDto findById(Long courseId);

    List<CourseDto> findAll(Integer offset, Integer limit);

    void deleteById(Long courseId);
}