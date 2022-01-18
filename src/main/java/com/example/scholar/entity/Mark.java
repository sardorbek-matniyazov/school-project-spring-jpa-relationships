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

    @OneToOne
    private Student student;

    @OneToOne
    private Teacher teacher;

    @OneToOne
    private Subject subject;

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
