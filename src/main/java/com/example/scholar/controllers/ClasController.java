package com.example.scholar.controllers;

import com.example.scholar.dto.ClasDto;
import com.example.scholar.entity.Clas;
import com.example.scholar.service.ClasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClasController {
    private final ClasService service;

    public ClasController(ClasService service) {
        this.service = service;
    }

    @GetMapping("/clas/all")
    public List<Clas> getAll(){
        return service.getAll();
    }

    @GetMapping("/clas/{id}")
    public Clas get(@PathVariable Long id){
        return service.findOne(id);
    }

    @PostMapping("/clas/add")
    public String pushOne(@RequestBody ClasDto clasDto){
        return service.save(clasDto);
    }

    @PutMapping("/clas/{id}")
    public String putOne(@RequestBody ClasDto clasDto, @PathVariable Long id){
        return service.saveOne(clasDto, id);
    }
    @DeleteMapping("/clas/{id}")
    public String deleteOne(@PathVariable Long id){
        return service.deleteOne(id);
    }


}
