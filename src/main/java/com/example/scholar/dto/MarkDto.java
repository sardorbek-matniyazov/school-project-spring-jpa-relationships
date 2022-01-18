package com.example.scholar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarkDto {
    private short ball;
    private Long student_id;
    private Long teacher_id;
    private String subject_name;
}
