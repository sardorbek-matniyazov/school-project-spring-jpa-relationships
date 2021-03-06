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

    // basically one teacher teaches in one school
    @ManyToOne()
    private School school;

    // one teacher can teach only one subject
    @ManyToOne(cascade = CascadeType.DETACH)
    private Subject subject;

    public Teacher(String first_name, String last_name, School school, Subject subject) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.school = school;
        this.subject = subject;
    }
}
