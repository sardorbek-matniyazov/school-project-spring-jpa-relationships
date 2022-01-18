package com.example.scholar.dto;

import lombok.Data;

@Data
public class StudentDto {

    // this class is to capture the request body from the ‘student’ path
    private String name;
    private String surname;
    private String name_school;
    private String address;
    private String class_name;
}
