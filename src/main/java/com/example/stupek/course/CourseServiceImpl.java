package com.example.stupek.course;

import com.example.stupek.exception.NotFoundException;
import com.example.stupek.person.Person;
import com.example.stupek.person.PersonRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private final PersonRepository personRepository;

    @Override
    @Transactional
    public CourseDto save(@Valid CourseDto courseDto,
                          @NotNull(message = "При попытки создания курса логин разработчика равен null")
                          String developerLogin) {
        Course newCourse = courseMapper.toCourse(courseDto);
        Person foundPerson = getPersonByLogin(developerLogin);
        newCourse.setDeveloper(foundPerson);
        courseRepository.save(newCourse);
        log.info("Course with id={} was created successfully", newCourse.getId());
        return courseMapper.toCourseDto(newCourse);
    }


    @Override
    @Transactional
    public CourseDto updateById(@Min(1) Long courseId, @Valid CourseDto updatedCourse) {
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
        if (updatedCourse.getIsOpen() != null && !foundCourse.getIsOpen().equals(updatedCourse.getIsOpen())) {
            foundCourse.setIsOpen(updatedCourse.getIsOpen());
        }
        foundCourse.setLastUpdate(LocalDateTime.now());
        courseRepository.save(foundCourse);
        log.info("Course with id={} was updated successfully", courseId);
        return courseMapper.toCourseDto(foundCourse);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseDto findById(@Min(1) Long courseId) {
        Course foundCourse = getCourseById(courseId);
        log.info("Course with id={} was found successfully", courseId);
        return courseMapper.toCourseDto(foundCourse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseDto> findAll(@Min(0) Integer offset, @Min(1) Integer limit) {
        List<Course> courses = courseRepository.findAllByIsOpenIsTrue(
                        PageRequest.of(offset, limit))
                .toList();
        log.info("Courses was found successfully");
        return courseListMapper.toCourseDtoList(courses);
    }

    @Override
    public List<CourseDto> findAllForAdmin() {
        List<Course> courses = courseRepository.findAll();
        log.info("Courses for admin was found successfully");
        return courseListMapper.toCourseDtoList(courses);
    }

    @Override
    public List<CourseDto> findDeveloperCourses(@NotBlank String loginDeveloper) {
        Person developer = getPersonByLogin(loginDeveloper);
        List<Course> developerCourses = courseRepository.findCourseByDeveloper(developer);
        log.info("Developer courses was found successfully");
        return courseListMapper.toCourseDtoList(developerCourses);
    }

    @Override
    @Transactional
    public void deleteById(@Min(1) Long courseId) {
        Course foundCourse = getCourseById(courseId);
        courseRepository.delete(foundCourse);
        log.info("Course with id={} was deleted successfully", courseId);
    }

    private Person getPersonByLogin(String developerLogin) {
        return personRepository.findPersonByLogin(developerLogin)
                .orElseThrow(() -> new NotFoundException("Person with login=" + developerLogin + " was not found."));
    }

    private Course getCourseById(Long courseId) {
        return courseRepository
                .findById(courseId)
                .orElseThrow(() ->
                        new NotFoundException("Course with id=" + courseId + " was not found"));
    }
}
