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

    private ClasRepo repo;
    private SubjectRepo subjectRepo;
    private SchoolRepo schoolRepo;

    public ClasService(ClasRepo repo, SubjectRepo subjectRepo, SchoolRepo schoolRepo) {
        this.repo = repo;
        this.subjectRepo = subjectRepo;
        this.schoolRepo = schoolRepo;
    }

    private List<Subject> subjects;

    public List<Clas> getAll() {
        return repo.findAll();
    }

    public Clas findOne(Long id) {
        if (!repo.existsById(id))
            return null;

        return repo.findById(id).get();
    }

    public String save(ClasDto clasDto) {

        if (repo.existsByName(clasDto.getName()) && schoolRepo.existsByNameAndAddress(clasDto.getName_school(), clasDto.getAddress()))
            return "This clas has already created";

        repo.save(new Clas(clasDto.getName(),
                getSchool(clasDto),
                getSubjects(clasDto.getSubjects()))
        );
        return "Clas successfully added";
    }

    public String saveOne(ClasDto clasDto, Long id) {

        if (!repo.existsById(id))
            return "There is no selected Clas";

        repo.save(new Clas(id,
                clasDto.getName(),
                getSchool(clasDto),
                getSubjects(clasDto.getSubjects()))
        );

        return "Clas has edited successfully";
    }

    public String deleteOne(Long id) {
        if (!repo.existsById(id))
            return "There is no selected Clas";

        repo.delete(repo.findById(id).get());

        return "clas successfully deleted";
    }

    private School getSchool(ClasDto clasDto){

        if(schoolRepo.findByNameAndAddress(clasDto.getName_school(), clasDto.getAddress()).isPresent())
            return schoolRepo.findByNameAndAddress(clasDto.getName_school(), clasDto.getAddress()).get();

        schoolRepo.save(new School(clasDto.getName_school(), clasDto.getAddress()));

        return schoolRepo.findByNameAndAddress(clasDto.getName_school(), clasDto.getAddress()).get();
    }

    private List<Subject> getSubjects(List<Subject> list){
        subjects = new ArrayList<>();

        list.forEach(subject1 -> {
            subjects.add(getSubject(subject1.getName()));
        });

        return subjects;
    }

    private Subject getSubject(String name){
        if(subjectRepo.existsByName(name))
            return subjectRepo.findByName(name).get();

        subjectRepo.save(new Subject(name));

        return subjectRepo.findByName(name).get();
    }
}
