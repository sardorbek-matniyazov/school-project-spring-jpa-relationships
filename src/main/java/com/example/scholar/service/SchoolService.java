package com.example.scholar.service;

import com.example.scholar.entity.School;
import com.example.scholar.repository.SchoolRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SchoolService {

    private final SchoolRepo repo;

    public SchoolService(SchoolRepo repo) {
        this.repo = repo;
    }

    public List<School> findAll(){
        return repo.findAll();
    }

    public String pushSchool(School school){
        if (checkByName(school.getName())){
            repo.save(school);
            return "Successfully added";
        }
        return "The school already taken! Please take another school";
    }

    public School findOne(long id){
        return repo.findById(id);
    }

    public String putOne(School school, long id){
        if (repo.findById(id) == null)return "there is no selected school";
        if (!checkByName(school.getName(), id))return "Please take another school name";
        school.setId(id);
        repo.save(school);
        return "School successfully edited !";
    }

    public String deleteOne(long id) {
        if (repo.findById(id) == null)return "there is no selected school";
        repo.delete(repo.findById(id));
        return "School successfully deleted";
    }

    private boolean checkByName(String name, Long id){
        for (School school: repo.findAll()) {
            if (school.getName().equals(name) && !Objects.equals(school.getId(), id))return false;
        }
        return true;
    }

    private boolean checkByName(String name){
        for (School school: repo.findAll()) {
            if (school.getName().equals(name))return false;
        }
        return true;
    }
}
