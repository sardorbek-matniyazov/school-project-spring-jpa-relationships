package com.example.scholar.service;

import com.example.scholar.dto.StudentDto;
import com.example.scholar.entity.Clas;
import com.example.scholar.entity.School;
import com.example.scholar.entity.Student;
import com.example.scholar.repository.ClasRepo;
import com.example.scholar.repository.SchoolRepo;
import com.example.scholar.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepo repo;
    private final SchoolRepo schoolRepo;
    private final ClasRepo clasRepo;

    public StudentService(StudentRepo repo, SchoolRepo schoolRepo, ClasRepo clasRepo) {
        this.repo = repo;
        this.schoolRepo = schoolRepo;
        this.clasRepo = clasRepo;
    }

    public List<Student> getAll() {
        return repo.findAll();
    }

    public Student getOne(Long id) {
        return repo.findById(id).isPresent() ? repo.findById(id).get() : null;
    }

    public String save(StudentDto dto) {
        if (!schoolRepo.existsByNameAndAddress(dto.getName_school(),
                dto.getAddress())
        ) return "this school is not been included";

        if (!clasRepo.existsByNameAndSchoolNameAndSchoolAddress(dto.getClass_name(),
                dto.getName_school(),
                dto.getAddress())) return "this Clas is not been included";

        repo.save(new Student(dto.getName(),
                dto.getSurname(),
                getSchool(dto.getName_school(), dto.getAddress()),
                getClas(dto.getClass_name(), dto.getName_school(), dto.getAddress()))
        );
        return "Student successfully added";
    }

    public String edit(StudentDto dto, Long id) {
        if (!repo.existsById(id)) return "there is no selected Student";
        if (!schoolRepo.existsByNameAndAddress(dto.getName_school(),
                dto.getAddress())
        ) return "this school is not been included";

        if (!clasRepo.existsByNameAndSchoolNameAndSchoolAddress(dto.getClass_name(),
                dto.getName_school(),
                dto.getAddress())) return "this Clas is not been included";

        repo.save(new Student(id,
                dto.getName(),
                dto.getSurname(),
                getSchool(dto.getName_school(), dto.getAddress()),
                getClas(dto.getClass_name(), dto.getName_school(), dto.getAddress()))
        );

        return "Student successfully edited";
    }

    public String delete(Long id) {
        if (!repo.existsById(id)) return "there is no selected Student";

        repo.deleteById(id);

        return "Student successfully deleted";
    }

    private School getSchool(String name, String address){
        return schoolRepo.findByNameAndAddress(name, address).get();
    }

    private Clas getClas(String clas_name, String name, String address){
        return clasRepo.findByNameAndSchoolNameAndSchoolAddress(clas_name, name, address).get();
    }

}
