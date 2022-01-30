package com.example.scholar.service;

import com.example.scholar.entity.School;
import com.example.scholar.repository.ClasRepo;
import com.example.scholar.repository.SchoolRepo;
import com.example.scholar.repository.StudentRepo;
import com.example.scholar.repository.TeacherRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SchoolService {

    // these repositories connect to database tables with hibernate and simplifies our work.
    // you can find it out by studying the wider hibernate
    private final SchoolRepo repo;
    private final StudentRepo studentRepo;
    private final ClasRepo clasRepo;
    private final TeacherRepo teacherRepo;

    // dependency injection
    public SchoolService(SchoolRepo repo, StudentRepo studentRepo, ClasRepo clasRepo, TeacherRepo teacherRepo) {
        this.repo = repo;
        this.studentRepo = studentRepo;
        this.clasRepo = clasRepo;
        this.teacherRepo = teacherRepo;
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

        // editing school
        school.setId(id);
        repo.save(school);
        return "School successfully edited !";
    }

    // deleting selected school
    public String deleteOne(long id) {
        if (repo.findById(id) == null)return "there is no selected school";
        if (deleteStudent(id)) return "error, some students were joined to this school";
        if (deleteClas(id)) return "error, some class were joined to this school";
        if (deleteTeacher(id)) return "error, some Teachers were joined to this school";

        //deleting school
        repo.deleteById(id);
        return "School successfully deleted";
    }

    private boolean deleteStudent(long id){
        return studentRepo.findBySchoolId(id).isPresent();
    }

    private boolean deleteTeacher(long id){
        return teacherRepo.findBySchoolId(id).isPresent();
    }

    private boolean deleteClas(long id){
        return clasRepo.findBySchoolId(id).isPresent();
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
