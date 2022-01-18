package com.example.scholar.controllers;

import com.example.scholar.dto.TeacherDto;
import com.example.scholar.entity.Teacher;
import com.example.scholar.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {

    // this is a Bean object that controls the logic of queries that come in my ‘teacher’ path.
    private TeacherService service;

    // this is dependency injection.
    public TeacherController(TeacherService service) {
        this.service = service;
    }

    // this request gets all teachers' data from database.
    @GetMapping("/teacher/all")
    public List<Teacher> getAll(){
        return service.getAll();
    }

    // this request gets selected teacher's data from database.
    @GetMapping("/teacher/{id}")
    public Teacher getOne(@PathVariable Long id){
        return service.get(id);
    }

    // this request adds a new teacher to the database.
    @PostMapping("/teacher/add")
    public String save(@RequestBody TeacherDto teacherDto){
        return service.save(teacherDto);
    }

    // this request edits selected teacher's data.
    @PutMapping("/teacher/{id}")
    public String put(@RequestBody TeacherDto teacherDto, @PathVariable Long id){
        return service.putOne(teacherDto, id);
    }

    // this request deletes selected teacher.
    @DeleteMapping("/teacher/{id}")
    public String deleteOne(@PathVariable Long id){
        return service.deleteOne(id);
    }
}
