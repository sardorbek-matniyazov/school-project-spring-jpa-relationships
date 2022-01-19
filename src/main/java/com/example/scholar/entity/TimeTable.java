package com.example.scholar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity()
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private String month;

    @Column(nullable = false)
    private String week_day;

    @Column(nullable = false)
    private String key;

    public TimeTable(int year, String month, String week_day, String key) {
        this.year = year;
        this.month = month;
        this.week_day = week_day;
        this.key = key;
    }
}
