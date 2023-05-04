package com.example.stupek.course;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public CourseDto createCourse(@RequestBody CourseDto courseDto) {
        return courseService.save(courseDto);
    }

    @PatchMapping("/{courseId}")
    public CourseDto updateById(@PathVariable Long courseId, @RequestBody CourseDto courseDto) {
        return courseService.updateById(courseId, courseDto);
    }

    @GetMapping("/{courseId}")
    public CourseDto findById(@PathVariable Long courseId) {
        return courseService.findById(courseId);
    }

    @GetMapping
    public List<CourseDto> findAll(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return courseService.findAll(offset, limit);
    }

    @DeleteMapping("/{courseId}")
    public void deleteById(@PathVariable Long courseId) {
        courseService.deleteById(courseId);
    }
}
