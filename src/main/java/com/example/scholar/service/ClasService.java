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

    private School school;
    private Subject subject;
    private List<Subject> subjects;

    public List<Clas> getAll() {
        return repo.findAll();
    }


    public Clas findOne(Long id) {
        return repo.findById(id).get();
    }

    public String save(ClasDto clasDto) {
        if (repo.existsByName(clasDto.getName()) && schoolRepo.existsByNameAndAddress(clasDto.getName_school(), clasDto.getAddress()))
            return "This clas has already created";
        repo.save(new Clas(clasDto.getName(), getSchool(clasDto), getSubjects(clasDto.getSubjects())));
        return "Clas successfully added";
    }

    private School getSchool(ClasDto clasDto){
        if(schoolRepo.findByNameAndAddress(clasDto.getName_school(), clasDto.getAddress()).isPresent())
            return schoolRepo.findByNameAndAddress(clasDto.getName_school(), clasDto.getAddress()).get();
        school = new School(clasDto.getName_school(), clasDto.getAddress());
        schoolRepo.save(school);
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
        subject = new Subject(name);
        subjectRepo.save(subject);
        return subjectRepo.findByName(name).get();
    }
}
