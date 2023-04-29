package com.example.stupek.course;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CourseDto {
    private String name;

    private String description;

    private String material;

    private Float price;

    private Boolean isOpen;
}
