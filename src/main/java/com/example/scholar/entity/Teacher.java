package com.example.scholar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false)
    private String last_name;

    @OneToOne
    private School school;

    @OneToOne
    private Subject subject;

    public Teacher(String first_name, String last_name, School school, Subject subject) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.school = school;
        this.subject = subject;
    }
}
