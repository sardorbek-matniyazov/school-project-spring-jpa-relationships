package com.example.scholar.controllers;

import com.example.scholar.entity.Subject;
import com.example.scholar.service.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectController {
    private final SubjectService service;

    public SubjectController(SubjectService service) {
        this.service = service;
    }

    @GetMapping("/subject/all")
    public List<Subject> getAll(){
        return service.getAll();
    }

    @GetMapping("/subject/{id}")
    public Subject getOne(@PathVariable Long id){
        return service.getOne(id);
    }

    @PostMapping("/subject/add")
    public String pushOne(@RequestBody Subject subject){
        return service.add(subject);
    }

    @PutMapping("/subject/{id}")
    public String putOne(@RequestBody Subject subject, @PathVariable Long id){
        return service.put(subject, id);
    }

    @DeleteMapping("subject/{id}")
    public String deleteOne(@PathVariable Long id){
        return service.delete(id);
    }
}
