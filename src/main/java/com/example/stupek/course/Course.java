package com.example.stupek.course;

import com.example.stupek.person.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Table(name = "courses")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Course {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "course_material")
    private String material;

    @Column(name = "price")
    private Float price;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @Column(name = "is_open", columnDefinition = "boolean default true")
    private Boolean isOpen;

    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Person developer;

    @ManyToMany(mappedBy = "userCourses")
    @ToString.Exclude
    private Set<Person> students;
}
