package com.example.scholar.service;

import com.example.scholar.entity.Subject;
import com.example.scholar.repository.SubjectRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SubjectService {

    private final SubjectRepo repo;

    public SubjectService(SubjectRepo repo) {
        this.repo = repo;
    }

    public List<Subject> getAll() {
        return repo.findAll();
    }

    public Subject getOne(Long id) {
        return repo.findById(id).isPresent() ? repo.findById(id).get() : null;
    }

    public String add(Subject subject) {
        if (searchByName(subject.getName()))return "This subject has already taken";

        repo.save(subject);

        return "Successfully added !";
    }

    public String put(Subject subject, Long id) {
        if (repo.findById(id).isEmpty())return "There is no selected subject";

        if (!searchByName(subject.getName(), id))return "Please take another subject name";

        subject.setId(id);
        repo.save(subject);

        return "Subject successfully edited";
    }

    private boolean searchByName(String name, Long id){
        for (Subject s: repo.findAll()) {
            if (s.getName().equals(name) && !Objects.equals(s.getId(), id))return false;
        }
        return true;
    }

    public String delete(Long id) {
        if (!repo.existsById(id))return "there is no selected subject";

        repo.deleteById(id);

        return "Subject successfully deleted";
    }

    private boolean searchByName(String name){
        for (Subject s: repo.findAll()) {
            if (s.getName().equals(name))return true;
        }
        return false;
    }
}
