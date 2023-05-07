package com.example.stupek.course;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CourseDto {
    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String material;

    @Min(0)
    private Float price;

    private Boolean isOpen;
}
