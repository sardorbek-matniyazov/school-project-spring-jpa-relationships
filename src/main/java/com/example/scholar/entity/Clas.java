package com.example.scholar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity()
public class Clas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // it connects as @OneToOne because one class cannot belong to two classes at the same time .
    @OneToOne
    private School school;

    // .. because several lessons can be taught in one class
    @ManyToMany
    private List<Subject> subjects;

    // this is secondary constructor
    public Clas(String name, School school, List<Subject> subjects) {
        this.name = name;
        this.school = school;
        this.subjects = subjects;
    }
}
