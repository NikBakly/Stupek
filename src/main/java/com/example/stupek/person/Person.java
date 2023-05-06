package com.example.stupek.person;

import com.example.stupek.course.Course;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Table(name = "persons")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "balance")
    private Float balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private PersonRole personRole;

    @ManyToMany
    @JoinTable(
            name = "users_courses",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    @ToString.Exclude
    private Set<Course> userCourses;

    @OneToMany(mappedBy = "developer")
    @ToString.Exclude
    private Set<Course> developedCourses;

    private LocalDateTime lastUpdate;
}
