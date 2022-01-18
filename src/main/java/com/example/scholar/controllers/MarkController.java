package com.example.scholar.controllers;

import com.example.scholar.dto.MarkDto;
import com.example.scholar.entity.Mark;
import com.example.scholar.service.MarkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MarkController {

    // this is a Bean object that controls the logic of queries that come in my ‘Mark’ path.
    private final MarkService service;

    public MarkController(MarkService service) {
        this.service = service;
    }

    // this request gets all marks' data from database.
    @GetMapping("/mark/all")
    public List<Mark> getAll(){
        return service.getAll();
    }

    // this request gets selected Mark's data from database.
    @GetMapping("/mark/{id}")
    public Mark getOne(@PathVariable Long id){
        return service.getOne(id);
    }

    // this request adds a new mark to the database.
    @PostMapping("/mark/add")
    public String save(@RequestBody MarkDto markDto){
        return service.save(markDto);
    }

    // this request edits selected mark's data.
    @PutMapping("/mark/{id}")
    public String edit(@RequestBody MarkDto markDto, @PathVariable Long id){
        return service.edit(markDto, id);
    }

    // this request deletes selected mark.
    @DeleteMapping("/mark/{id}")
    public String delete(@PathVariable Long id){
        return service.delete(id);
    }

}
