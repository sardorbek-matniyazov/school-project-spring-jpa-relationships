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

    @OneToOne
    private School school;

    @ManyToMany
    private List<Subject> subjects;

    public Clas(String name, School school, List<Subject> subjects) {
        this.name = name;
        this.school = school;
        this.subjects = subjects;
    }
}
