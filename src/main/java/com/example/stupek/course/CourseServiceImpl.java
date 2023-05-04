package com.example.stupek.course;

import com.example.stupek.exception.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Validated
public class CourseServiceImpl implements CourseService {
    private final CourseMapper courseMapper;
    private final CourseListMapper courseListMapper;
    private final CourseRepository courseRepository;

    @Override
    public CourseDto save(@Valid CourseDto courseDto) {
        Course newCourse = courseMapper.toCourse(courseDto);
        courseRepository.save(newCourse);
        log.info("Course with id={} was created successfully", newCourse.getId());
        return courseMapper.toCourseDto(newCourse);
    }

    @Override
    public CourseDto updateById(Long courseId, @Valid CourseDto updatedCourse) {
        Course foundCourse = getCourseById(courseId);
        if (!foundCourse.getName().equals(updatedCourse.getName())) {
            foundCourse.setName(updatedCourse.getName());
        }
        if (updatedCourse.getDescription() != null && !foundCourse.getDescription().equals(updatedCourse.getDescription())) {
            foundCourse.setDescription(updatedCourse.getDescription());
        }
        if (!foundCourse.getMaterial().equals(updatedCourse.getMaterial())) {
            foundCourse.setMaterial(updatedCourse.getMaterial());
        }
        if (!foundCourse.getPrice().equals(updatedCourse.getPrice())) {
            foundCourse.setPrice(updatedCourse.getPrice());
        }
        if (updatedCourse.getIsOpen() != null && !foundCourse.getIsOpen().equals(updatedCourse.getIsOpen())) {
            foundCourse.setIsOpen(updatedCourse.getIsOpen());
        }
        foundCourse.setLastUpdate(LocalDateTime.now());
        courseRepository.save(foundCourse);
        log.info("Course with id={} was updated successfully", courseId);
        return courseMapper.toCourseDto(foundCourse);
    }

    @Override
    public CourseDto findById(Long courseId) {
        Course foundCourse = getCourseById(courseId);
        log.info("Course with id={} was found successfully", courseId);
        return courseMapper.toCourseDto(foundCourse);
    }

    @Override
    public List<CourseDto> findAll(Integer offset, Integer limit) {
        List<Course> courses = courseRepository.findAll(
                        PageRequest.of(
                                offset,
                                limit))
                .toList();
        log.info("Courses was found successfully");
        return courseListMapper.toCourseDtoList(courses);
    }

    @Override
    public void deleteById(Long courseId) {
        Course foundCourse = getCourseById(courseId);
        courseRepository.delete(foundCourse);
        log.info("Course with id={} was deleted successfully", courseId);
    }

    private Course getCourseById(Long courseId) {
        return courseRepository
                .findById(courseId)
                .orElseThrow(() -> {
                    log.warn("Course with id={} was not found", courseId);
                    return new NotFoundException("Course with id=" + courseId + " was not found");
                });
    }
}
