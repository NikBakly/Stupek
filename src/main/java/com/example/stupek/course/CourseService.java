package com.example.stupek.course;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import java.util.List;

public interface CourseService {
    CourseDto save(@Valid CourseDto courseDto);

    CourseDto updateById(@Min(1) Long courseId, @Valid CourseDto updatedCourse);

    CourseDto findById(@Min(1) Long courseId);

    List<CourseDto> findAll(@Min(0) Integer offset, @Min(1) Integer limit);

    void deleteById(@Min(1) Long courseId);
}