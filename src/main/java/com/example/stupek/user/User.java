package com.example.stupek.user;

import com.example.stupek.course.Course;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String password;

    private String email;

    private Integer balance;

    @OneToMany
    @ToString.Exclude
    Set<Course> courses;

    @OneToMany
    @ToString.Exclude
    private Set<Course> developedCourses;

    private LocalDateTime lastUpdate;
}
