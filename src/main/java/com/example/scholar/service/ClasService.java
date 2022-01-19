package com.example.scholar.service;

import com.example.scholar.dto.ClasDto;
import com.example.scholar.entity.Clas;
import com.example.scholar.entity.School;
import com.example.scholar.entity.Subject;
import com.example.scholar.repository.ClasRepo;
import com.example.scholar.repository.SchoolRepo;
import com.example.scholar.repository.SubjectRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClasService {

    // these repositories connect to database tables with hibernate and simplifies our work.
    // you can find it out by studying the wider hibernate
    private ClasRepo repo;
    private SubjectRepo subjectRepo;
    private SchoolRepo schoolRepo;

    public ClasService(ClasRepo repo, SubjectRepo subjectRepo, SchoolRepo schoolRepo) {
        this.repo = repo;
        this.subjectRepo = subjectRepo;
        this.schoolRepo = schoolRepo;
    }

    private List<Subject> subjects;

    // this gets all classes from database
    public List<Clas> getAll() {
        return repo.findAll();
    }

    // this method takes the class in the database selected with the id
    public Clas findOne(Long id) {

        // if there is no class with the selected id in the databases
        if (!repo.existsById(id))
            return null;

        return repo.findById(id).get();
    }

    // saving new class
    public String save(ClasDto clasDto) {

        // here compares the classes created earlier with the class you want to create
        if (repo.existsByName(clasDto.getName()) && schoolRepo.existsByNameAndAddress(clasDto.getName_school(), clasDto.getAddress()))
            return "This clas has already created";

        // saving the class
        repo.save(new Clas(clasDto.getName(),
                getSchool(clasDto),
                getSubjects(clasDto.getSubjects()))
        );
        return "Clas successfully added";
    }

    // editing with id the class created earlier
    public String saveOne(ClasDto clasDto, Long id) {

        // if there is no class with the selected id in the databases
        if (!repo.existsById(id))
            return "There is no selected Clas";

        // saving the existed class with new attributes
        repo.save(new Clas(id,
                clasDto.getName(),
                getSchool(clasDto),
                getSubjects(clasDto.getSubjects()))
        );

        return "Clas has edited successfully";
    }

    // deleting with id
    public String deleteOne(Long id) {

        // if there is no class with the selected id in the databases
        if (!repo.existsById(id))
            return "There is no selected Clas";

        // deleting the class
        repo.delete(repo.findById(id).get());

        return "clas successfully deleted";
    }

    // get school from database
    private School getSchool(ClasDto clasDto){
        // if there is no school we want to include in the database, it creates a new school.
        // if school we wanted is exists, it gives the school

        if(schoolRepo.findByNameAndAddress(clasDto.getName_school(), clasDto.getAddress()).isPresent())
            return schoolRepo.findByNameAndAddress(clasDto.getName_school(), clasDto.getAddress()).get();

        schoolRepo.save(new School(clasDto.getName_school(), clasDto.getAddress()));

        return schoolRepo.findByNameAndAddress(clasDto.getName_school(), clasDto.getAddress()).get();
    }

    // getting the subjects list
    private List<Subject> getSubjects(List<Subject> list){
        subjects = new ArrayList<>();

        // it obtains a list of subjects to be taught in the class from the database.
        list.forEach(subject1 -> {
            subjects.add(getSubject(subject1.getName()));
        });

        return subjects;
    }

    // getting the subject
    private Subject getSubject(String name){
        // if there is no subject we want to include in the database, it creates a new subject.
        // if subject we wanted is exists, it gives the subject

        if(subjectRepo.existsByName(name))
            return subjectRepo.findByName(name).get();

        subjectRepo.save(new Subject(name));

        return subjectRepo.findByName(name).get();
    }
}
