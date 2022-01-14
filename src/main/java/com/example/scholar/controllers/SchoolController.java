package com.example.scholar.controllers;

import com.example.scholar.entity.School;
import com.example.scholar.service.SchoolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class SchoolController {

    private final SchoolService service;

    public SchoolController(SchoolService service) {
        this.service = service;
    }

    @GetMapping("/school/all")
    public List<School> getAll(){
        return service.findAll();
    }

    @GetMapping("/school/{id}")
    public School getOne(@PathVariable Long id){
        return service.findOne(id);
    }

    @PostMapping("/school/add")
    public String pushOne(@RequestBody School school){
        return service.pushSchool(school);
    }

    @PutMapping("/school/{id}")
    public String putOne(@PathVariable Long id, @RequestBody School school){
        return service.putOne(school, id);
    }

    @DeleteMapping("/school/{id}")
    public String deleteOne(@PathVariable long id){
        return service.deleteOne(id);
    }
}
