package com.example.stupek.course;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface CourseService {
    CourseDto save(@Valid CourseDto courseDto,
                   @NotNull(message = "При попытки создания курса логин разработчика равен null")
                   String developerLogin);

    CourseDto updateById(@Min(1) Long courseId, @Valid CourseDto updatedCourse);

    CourseDto findById(@Min(1) Long courseId);

    List<CourseDto> findAll(@Min(0) Integer offset, @Min(1) Integer limit);

    List<CourseDto> findAllForAdmin();

    List<CourseDto> findDeveloperCourses(@NotBlank String loginDeveloper);

    void deleteById(@Min(1) Long courseId);
}