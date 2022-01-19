package com.example.scholar.controllers;

import com.example.scholar.dto.ClasDto;
import com.example.scholar.entity.Clas;
import com.example.scholar.service.ClasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClasController {

    // this is a Bean object that controls the logic of queries that come in my ‘class’ path.
    private final ClasService service;

    // this is dependency injection.
    public ClasController(ClasService service) {
        this.service = service;
    }

    // this request gets all classes' data from database.
    @GetMapping("/clas/all")
    public List<Clas> getAll(){
        return service.getAll();
    }

    // this request gets selected class's data from database.
    @GetMapping("/clas/{id}")
    public Clas get(@PathVariable Long id){
        return service.findOne(id);
    }

    // this request adds a new class to the database.
    @PostMapping("/clas/add")
    public String pushOne(@RequestBody ClasDto clasDto){
        return service.save(clasDto);
    }

    // this request edits selected class's data.
    @PutMapping("/clas/{id}")
    public String putOne(@RequestBody ClasDto clasDto, @PathVariable Long id){
        return service.saveOne(clasDto, id);
    }

    // this request deletes selected class.
    @DeleteMapping("/clas/{id}")
    public String deleteOne(@PathVariable Long id){
        return service.deleteOne(id);
    }
}
