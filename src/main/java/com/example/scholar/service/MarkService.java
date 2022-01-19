package com.example.scholar.service;

import com.example.scholar.dto.MarkDto;
import com.example.scholar.entity.*;
import com.example.scholar.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MarkService {

    // these repositories connect to database tables with hibernate and simplifies our work.
    // you can find it out by studying the wider hibernate
    private final MarkRepo repo;
    private final StudentRepo studentRepo;
    private final TeacherRepo teacherRepo;
    private final TimeTableRepo timeTableRepo;
    private final SubjectRepo subjectRepo;

    public MarkService(MarkRepo repo, StudentRepo studentRepo, TeacherRepo teacherRepo, TimeTableRepo timeTableRepo, SubjectRepo subjectRepo) {
        this.repo = repo;
        this.studentRepo = studentRepo;
        this.teacherRepo = teacherRepo;
        this.timeTableRepo = timeTableRepo;
        this.subjectRepo = subjectRepo;
    }

    // this gets all marks from database
    public List<Mark> getAll(){
        return repo.findAll();
    }

    // this method takes the mark in the database selected with the id
    public Mark getOne(Long id) {

        // if there is no mark with the selected id in the databases
        return repo.existsById(id) ? repo.getById(id) : null;
    }

    // saving new mark
    public String save(MarkDto markDto) {
        // if the assessment to be made does not meet the requirements
        if (markDto.getBall() > 5 && markDto.getBall() < 1) return "Your ball isn't correct";

        // if there is no student with the selected id in the databases
        if (!studentRepo.existsById(markDto.getStudent_id())) return "There is no Student with that id";

        // if there is no teacher with the selected id in the databases
        if (!teacherRepo.existsById(markDto.getTeacher_id())) return "There is no Teacher with that id";

        // if there is no subject with the selected id in the databases
        if (!subjectRepo.existsByName(markDto.getSubject_name())) return "There is no Subject";

        // saving the new mark
        repo.save(new Mark(markDto.getBall(),
                getStudent(markDto.getStudent_id()),
                getTeacher(markDto.getTeacher_id()),
                getSubject(markDto.getSubject_name()),
                getDate())
        );
        return "Your Mark has marked successfully";
    }

    // editing the mark
    public String edit(MarkDto markDto, Long id) {

        // if there is no mark with the selected id in the databases
        if (!repo.existsById(id)) return "There is no selected mark";

        // if the assessment to be made does not meet the requirements
        if (markDto.getBall() > 5 && markDto.getBall() < 1) return "Your ball isn't correct";

        // if there is no student with the selected id in the databases
        if (!studentRepo.existsById(markDto.getStudent_id())) return "There is no Student with that id";

        // if there is no teacher with the selected id in the databases
        if (!teacherRepo.existsById(markDto.getTeacher_id())) return "There is no Teacher with that id";

        // if there is no subject with the selected id in the databases
        if (!subjectRepo.existsByName(markDto.getSubject_name())) return "There is no Subject";

        // editing the mark
        repo.save(new Mark(id,
                markDto.getBall(),
                getStudent(markDto.getStudent_id()),
                getTeacher(markDto.getTeacher_id()),
                getSubject(markDto.getSubject_name()),
                getDate())
        );
        return "Your Mark has marked successfully";
    }

    // deleting selected mark
    public String delete(Long id) {

        // if there is no mark with the selected id in the databases
        if (!repo.existsById(id)) return "There is no selected mark";

        repo.deleteById(id);
        return "Mark successfully deleted";
    }

    // getting student with selected id
    private Student getStudent(long id){
        return studentRepo.getById(id);
    }

    // getting teacher with selected id
    private Teacher getTeacher(long id){
        return teacherRepo.getById(id);
    }

    // getting subject with selected id
    private Subject getSubject(String name){
        return subjectRepo.findByName(name).get();
    }

    // getting time with current key
    private TimeTable getDate(){
        return timeTableRepo.findByKey(getNowTime()).get();
    }

    // saving to database current time and getting current key.
    private String key;
    private String getNowTime(){
        key = String.valueOf(System.currentTimeMillis());
        timeTableRepo.save(new TimeTable(LocalDate.now().getYear(),
                LocalDate.now().getMonth().name(),
                LocalDate.now().getDayOfWeek().name(),
                key)
        );
        return key;
    }

}
