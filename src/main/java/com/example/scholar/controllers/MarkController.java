package com.example.scholar.controllers;

import com.example.scholar.dto.MarkDto;
import com.example.scholar.entity.Mark;
import com.example.scholar.service.MarkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MarkController {

    private final MarkService service;

    public MarkController(MarkService service) {
        this.service = service;
    }

    @GetMapping("/mark/all")
    public List<Mark> getAll(){
        return service.getAll();
    }

    @GetMapping("/mark/{id}")
    public Mark getOne(@PathVariable Long id){
        return service.getOne(id);
    }

    @PostMapping("/mark/add")
    public String save(@RequestBody MarkDto markDto){
        return service.save(markDto);
    }

    @PutMapping("/mark/{id}")
    public String edit(@RequestBody MarkDto markDto, @PathVariable Long id){
        return service.edit(markDto, id);
    }

    @DeleteMapping("/mark/{id}")
    public String delete(@PathVariable Long id){
        return service.delete(id);
    }

}
