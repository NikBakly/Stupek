package com.example.stupek.course;

import com.example.stupek.user.User;
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
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String material;

    private Integer price;

    private LocalDateTime lastUpdate;

    @ManyToOne
    private User developer;

    @ManyToMany
    @ToString.Exclude
    private Set<User> students;

}
