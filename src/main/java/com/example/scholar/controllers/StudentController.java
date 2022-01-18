package com.example.scholar.controllers;

import com.example.scholar.dto.StudentDto;
import com.example.scholar.entity.Student;
import com.example.scholar.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/student/all")
    public List<Student> getAll(){
        return service.getAll();
    }

    @GetMapping("/student/{id}")
    public Student getOne(@PathVariable Long id){
        return service.getOne(id);

    }

    @PostMapping("/student/add")
    public String save(@RequestBody StudentDto dto){
        return service.save(dto);

    }

    @PutMapping("/student/{id}")
    public String edit(@RequestBody StudentDto dto, @PathVariable Long id){
        return service.edit(dto, id);

    }
    @DeleteMapping("/student/{id}")
    public String delete(@PathVariable Long id){
        return service.delete(id);

    }

}
