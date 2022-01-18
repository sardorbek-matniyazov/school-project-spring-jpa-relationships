package com.example.scholar.controllers;

import com.example.scholar.dto.StudentDto;
import com.example.scholar.entity.Student;
import com.example.scholar.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    // this is a Bean object that controls the logic of queries that come in my ‘student’ path.
    private final StudentService service;

    // this is dependency injection.
    public StudentController(StudentService service) {
        this.service = service;
    }

    // this request gets all students' data from database.
    @GetMapping("/student/all")
    public List<Student> getAll(){
        return service.getAll();
    }

    // this request gets selected school's data from database.
    @GetMapping("/student/{id}")
    public Student getOne(@PathVariable Long id){
        return service.getOne(id);

    }

    // this request adds a new school to the database.
    @PostMapping("/student/add")
    public String save(@RequestBody StudentDto dto){
        return service.save(dto);

    }

    // this request edits selected school's data.
    @PutMapping("/student/{id}")
    public String edit(@RequestBody StudentDto dto, @PathVariable Long id){
        return service.edit(dto, id);

    }

    // this request deletes selected school.
    @DeleteMapping("/student/{id}")
    public String delete(@PathVariable Long id){
        return service.delete(id);

    }

}
