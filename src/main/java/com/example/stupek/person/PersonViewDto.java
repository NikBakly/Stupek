package com.example.stupek.person;

import com.example.stupek.course.CourseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonViewDto {
    private String login;

    private String email;

    private Float balance;

    private PersonRole personRole;

    private PersonStatus personStatus;

    private Set<CourseDto> userCourses;

    private Set<CourseDto> developedCourses;

    private LocalDateTime lastUpdate;

}
