package com.example.scholar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Check(constraints = "ball > 0 AND ball < 6")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1)
    private short ball;

    // this connects as @OneToOne because one mark cannot belong to two students at the same time.
    @OneToOne
    private Student student;

    // a single grade can only apply to one teacher
    @OneToOne
    private Teacher teacher;

    // a single grade can only apply to one subject
    @OneToOne
    private Subject subject;

    // this is a Evaluation date
    @OneToOne
    private TimeTable time;

    public Mark(short ball, Student student, Teacher teacher, Subject subject, TimeTable time) {
        this.ball = ball;
        this.student = student;
        this.teacher = teacher;
        this.subject = subject;
        this.time = time;
    }
}
