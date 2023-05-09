package com.example.stupek.course;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseRestController {
    private final CourseService courseService;

    @PostMapping
    @PreAuthorize("hasAuthority('developers:write')")
    public CourseDto createCourse(@RequestBody CourseDto courseDto) {
        return courseService.save(courseDto);
    }

    @PatchMapping("/{courseId}")
    @PreAuthorize("hasAnyAuthority('developers:write', 'admins:write')")
    public CourseDto updateById(@PathVariable Long courseId, @RequestBody CourseDto courseDto) {
        return courseService.updateById(courseId, courseDto);
    }

    @GetMapping("/{courseId}")
    @PreAuthorize("hasAuthority('users:read')")
    public CourseDto findById(@PathVariable Long courseId) {
        return courseService.findById(courseId);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('users:read')")
    public List<CourseDto> findAll(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                   @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
        return courseService.findAll(offset, limit);
    }

    @DeleteMapping("/{courseId}")
    @PreAuthorize("hasAnyAuthority('developers:write', 'admins:write')")
    public void deleteById(@PathVariable Long courseId) {
        courseService.deleteById(courseId);
    }
}
