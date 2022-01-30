package com.example.scholar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.profile.Fetch;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    // this connects as @OneToOne because one student can only attend one school
    @ManyToOne(cascade = CascadeType.DETACH)
    private School school;

    // many students may belong to the same class
    @ManyToOne(cascade = CascadeType.DETACH)
    private Clas clas;

    public Student(String name, String surname, School school, Clas clas) {
        this.name = name;
        this.surname = surname;
        this.school = school;
        this.clas = clas;
    }
}
