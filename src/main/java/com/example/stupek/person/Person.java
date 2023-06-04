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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    PersonStatus personStatus;

    @OneToMany(mappedBy = "developer")
    @ToString.Exclude
    private Set<Course> developedCourses;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
