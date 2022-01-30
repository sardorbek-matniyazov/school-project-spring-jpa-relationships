package com.example.scholar.service;

import com.example.scholar.dto.StudentDto;
import com.example.scholar.entity.Clas;
import com.example.scholar.entity.Mark;
import com.example.scholar.entity.School;
import com.example.scholar.entity.Student;
import com.example.scholar.repository.ClasRepo;
import com.example.scholar.repository.MarkRepo;
import com.example.scholar.repository.SchoolRepo;
import com.example.scholar.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentService {

    // these repositories connect to database tables with hibernate and simplifies our work.
    // you can find it out by studying the wider hibernate
    private final StudentRepo repo;
    private final SchoolRepo schoolRepo;
    private final ClasRepo clasRepo;
    private final MarkRepo markRepo;

    public StudentService(StudentRepo repo, SchoolRepo schoolRepo, ClasRepo clasRepo, MarkRepo markRepo) {
        this.repo = repo;
        this.schoolRepo = schoolRepo;
        this.clasRepo = clasRepo;
        this.markRepo = markRepo;
    }


    // this gets all students from database
    public List<Student> getAll() {
        return repo.findAll();
    }

    // this method takes the student in the database selected with the id
    public Student getOne(Long id) {
        return repo.findById(id).isPresent() ? repo.findById(id).get() : null;
    }

    // saving new student
    public String save(StudentDto dto) {

        // if new student's school isn't exists in database
        if (!schoolRepo.existsByNameAndAddress(dto.getName_school(),
                dto.getAddress())
        ) return "this school is not been included";

        // if new student's class isn't exists in database
        if (!clasRepo.existsByNameAndSchoolNameAndSchoolAddress(dto.getClass_name(),
                dto.getName_school(),
                dto.getAddress())) return "this Clas is not been included";

        // saving the student
        repo.save(new Student(dto.getName(),
                dto.getSurname(),
                getSchool(dto.getName_school(), dto.getAddress()),
                getClas(dto.getClass_name(), dto.getName_school(), dto.getAddress()))
        );
        return "Student successfully added";
    }

    // editing the student
    public String edit(StudentDto dto, Long id) {

        // if there is no student with the selected id in the databases
        if (!repo.existsById(id)) return "there is no selected Student";

        // checking schools with hibernate
        // if the edited class isn't exists in database
        if (!schoolRepo.existsByNameAndAddress(dto.getName_school(),
                dto.getAddress())
        ) return "this school is not been included";

        // checking class with hibernate
        // if the edited class isn't exists in database
        if (!clasRepo.existsByNameAndSchoolNameAndSchoolAddress(dto.getClass_name(),
                dto.getName_school(),
                dto.getAddress())) return "this Clas is not been included";

        // editing student
        repo.save(new Student(id,
                dto.getName(),
                dto.getSurname(),
                getSchool(dto.getName_school(), dto.getAddress()),
                getClas(dto.getClass_name(), dto.getName_school(), dto.getAddress()))
        );

        return "Student successfully edited";
    }


    // deleting selected mark
    public String delete(Long id) {

        // if there is no student with the selected id in the databases
        if (!repo.existsById(id)) return "there is no selected Student";

        deleteMarks(id);
        repo.deleteById(id);
        return "Student successfully deleted";
    }

    // deleting the student's marks
    private void deleteMarks(Long id){
        for (Mark mark: markRepo.findAll()) {
            if (Objects.equals(mark.getStudent().getId(), id)) markRepo.delete(mark);
        }
    }

    // getting school with hibernate
    private School getSchool(String name, String address){
        return schoolRepo.findByNameAndAddress(name, address).get();
    }

    // getting class with hibernate
    private Clas getClas(String clas_name, String name, String address){
        return clasRepo.findByNameAndSchoolNameAndSchoolAddress(clas_name, name, address).get();
    }

}
