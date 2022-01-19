package com.example.scholar.service;

import com.example.scholar.entity.Subject;
import com.example.scholar.repository.SubjectRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SubjectService {

    // these repositories connect to database tables with hibernate and simplifies our work.
    // you can find it out by studying the wider hibernate
    private final SubjectRepo repo;

    public SubjectService(SubjectRepo repo) {
        this.repo = repo;
    }

    // this gets all subjects from database
    public List<Subject> getAll() {
        return repo.findAll();
    }

    // this method takes the subject in the database selected with the id
    public Subject getOne(Long id) {
        return repo.findById(id).isPresent() ? repo.findById(id).get() : null;
    }

    // saving new subject
    public String add(Subject subject) {

        // checking with name . there can be no two subjects of the same name
        if (searchByName(subject.getName()))return "This subject has already taken";

        repo.save(subject);
        return "Successfully added !";
    }

    // editing the subject
    public String put(Subject subject, Long id) {

        // if there is no subject with the selected id in the databases
        if (repo.findById(id).isEmpty())return "There is no selected subject";

        // checking with name . there can be no two subjects of the same name
        if (!searchByName(subject.getName(), id))return "Please take another subject name";

        // editing the subject
        subject.setId(id);
        repo.save(subject);

        return "Subject successfully edited";
    }

    // checking with name without hibernate. it's too long way
    private boolean searchByName(String name, Long id){
        for (Subject s: repo.findAll()) {
            if (s.getName().equals(name) && !Objects.equals(s.getId(), id))return false;
        }
        return true;
    }

    // deleting selected subject
    public String delete(Long id) {

        // if there is no subject with the selected id in the databases
        if (!repo.existsById(id))return "there is no selected subject";

        repo.deleteById(id);
        return "Subject successfully deleted";
    }

    // checking with name without hibernate. it's too long way
    private boolean searchByName(String name){
        for (Subject s: repo.findAll()) {
            if (s.getName().equals(name))return true;
        }
        return false;
    }
}
