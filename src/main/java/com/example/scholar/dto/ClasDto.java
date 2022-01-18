package com.example.scholar.dto;

import com.example.scholar.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClasDto {

    // this class is to capture the request body from the ‘class’ path
    private String name;
    private String name_school;
    private String address;
    private List<Subject> subjects;
}
