package com.example.scholar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity()
public class TimeTable {
    @Id
    private Long id;

    @Column
    private int year;

    @Column
    private int month;

    @Column
    private int week_day;
}
