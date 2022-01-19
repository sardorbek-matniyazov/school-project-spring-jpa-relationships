package com.example.scholar.service;

import com.example.scholar.entity.School;
import com.example.scholar.repository.SchoolRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SchoolService {

    // these repositories connect to database tables with hibernate and simplifies our work.
    // you can find it out by studying the wider hibernate
    private final SchoolRepo repo;

    // dependency injection
    public SchoolService(SchoolRepo repo) {
        this.repo = repo;
    }

    // this gets all schools from database
    public List<School> findAll(){
        return repo.findAll();
    }

    // saving school
    public String pushSchool(School school){
        if (checkByName(school.getName())){
            repo.save(school);
            return "Successfully added";
        }
        return "The school already taken! Please take another school";
    }

    // this method takes the school in the database selected with the id
    public School findOne(long id){
        return repo.findById(id);
    }

    // editing the selected school
    public String putOne(School school, long id){

        // if there is no school with the selected id in the databases
        if (repo.findById(id) == null)return "there is no selected school";

        // if school already exists
        if (!checkByName(school.getName(), id))return "Please take another school name";

        school.setId(id);
        repo.save(school);
        return "School successfully edited !";
    }

    // deleting selected mark
    public String deleteOne(long id) {
        if (repo.findById(id) == null)return "there is no selected school";
        repo.delete(repo.findById(id));
        return "School successfully deleted";
    }

    // checking schools without hibernate
    private boolean checkByName(String name, Long id){
        for (School school: repo.findAll()) {
            if (school.getName().equals(name) && !Objects.equals(school.getId(), id))return false;
        }
        return true;
    }

    // checking schools without hibernate
    private boolean checkByName(String name){
        for (School school: repo.findAll()) {
            if (school.getName().equals(name))return false;
        }
        return true;
    }
}
