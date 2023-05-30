package com.example.stupek.course;

import com.example.stupek.person.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findAllByIsOpenIsTrue(Pageable pageable);

    List<Course> findCourseByDeveloper(Person developer);
}
