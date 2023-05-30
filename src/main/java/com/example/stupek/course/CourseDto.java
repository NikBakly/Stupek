package com.example.stupek.course;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CourseDto {
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String material;

    private Boolean isOpen;
}
