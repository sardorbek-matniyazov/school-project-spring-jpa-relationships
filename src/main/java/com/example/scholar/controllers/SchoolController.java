package com.example.scholar.controllers;

import com.example.scholar.entity.School;
import com.example.scholar.service.SchoolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class SchoolController {

    // this is a Bean object that controls the logic of queries that come in my ‘school’ path.
    private final SchoolService service;

    public SchoolController(SchoolService service) {
        this.service = service;
    }

    // this request gets all schools' data from database.
    @GetMapping("/school/all")
    public List<School> getAll(){
        return service.findAll();
    }

    // this request gets selected school's data from database.
    @GetMapping("/school/{id}")
    public School getOne(@PathVariable Long id){
        return service.findOne(id);
    }

    // this request adds a new school to the database.
    @PostMapping("/school/add")
    public String pushOne(@RequestBody School school){
        return service.pushSchool(school);
    }

    // this request edits selected school's data.
    @PutMapping("/school/{id}")
    public String putOne(@PathVariable Long id, @RequestBody School school){
        return service.putOne(school, id);
    }

    // this request deletes selected school.
    @DeleteMapping("/school/{id}")
    public String deleteOne(@PathVariable long id){
        return service.deleteOne(id);
    }
}
