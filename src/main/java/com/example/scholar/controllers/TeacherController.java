package com.example.scholar.controllers;

import com.example.scholar.dto.TeacherDto;
import com.example.scholar.entity.Teacher;
import com.example.scholar.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {
    private TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @GetMapping("/teacher/all")
    public List<Teacher> getAll(){
        return service.getAll();
    }

    @GetMapping("/teacher/{id}")
    public Teacher getOne(@PathVariable Long id){
        return service.get(id);
    }

    @PostMapping("/teacher/add")
    public String save(@RequestBody TeacherDto teacherDto){
        return service.save(teacherDto);
    }

    @PutMapping("/teacher/{id}")
    public String put(@RequestBody TeacherDto teacherDto, @PathVariable Long id){
        return service.putOne(teacherDto, id);
    }

    @DeleteMapping("/teacher/{id}")
    public String deleteOne(@PathVariable Long id){
        return service.deleteOne(id);
    }
}
