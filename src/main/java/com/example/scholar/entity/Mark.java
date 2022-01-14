package com.example.scholar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Check(constraints = "ball > 0 AND ball < 6")
public class Mark {

    @Id
    private Long id;

    @Column(length = 1)
    private int ball;

    @OneToOne
    private Subject subject;

    @OneToOne
    private TimeTable time;

}
