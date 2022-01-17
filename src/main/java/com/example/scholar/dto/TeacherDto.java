package com.example.scholar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
    private String first_name;
    private String last_name;
    private String name;
    private String address;
    private String name_s;
}
