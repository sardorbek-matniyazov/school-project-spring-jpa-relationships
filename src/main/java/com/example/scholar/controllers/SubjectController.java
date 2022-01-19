package com.example.scholar.controllers;

import com.example.scholar.entity.Subject;
import com.example.scholar.service.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectController {

    // this is a Bean object that controls the logic of queries that come in my ‘subject’ path.
    private final SubjectService service;

    // this is dependency injection.
    public SubjectController(SubjectService service) {
        this.service = service;
    }

    // this request gets all subject' data from database.
    @GetMapping("/subject/all")
    public List<Subject> getAll(){
        return service.getAll();
    }

    // this request gets selected subject's data from database.
    @GetMapping("/subject/{id}")
    public Subject getOne(@PathVariable Long id){
        return service.getOne(id);
    }

    // this request adds a new subject to the database.
    @PostMapping("/subject/add")
    public String pushOne(@RequestBody Subject subject){
        return service.add(subject);
    }

    // this request edits selected subject's data.
    @PutMapping("/subject/{id}")
    public String putOne(@RequestBody Subject subject, @PathVariable Long id){
        return service.put(subject, id);
    }

    // this request deletes selected subject.
    @DeleteMapping("subject/{id}")
    public String deleteOne(@PathVariable Long id){
        return service.delete(id);
    }
}
