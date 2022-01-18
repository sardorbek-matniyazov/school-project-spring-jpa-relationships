package com.example.scholar.service;

import com.example.scholar.dto.MarkDto;
import com.example.scholar.entity.*;
import com.example.scholar.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MarkService {

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

    public List<Mark> getAll(){
        return repo.findAll();
    }

    public Mark getOne(Long id) {
        return repo.existsById(id) ? repo.getById(id) : null;
    }


    public String save(MarkDto markDto) {
        if (markDto.getBall() > 5 && markDto.getBall() < 1) return "Your ball isn't correct";
        if (!studentRepo.existsById(markDto.getStudent_id())) return "There is no Student with that id";
        if (!teacherRepo.existsById(markDto.getTeacher_id())) return "There is no Teacher with that id";
        if (!subjectRepo.existsByName(markDto.getSubject_name())) return "There is no Subject";
        repo.save(new Mark(markDto.getBall(),
                getStudent(markDto.getStudent_id()),
                getTeacher(markDto.getTeacher_id()),
                getSubject(markDto.getSubject_name()),
                getDate())
        );
        return "Your Mark has marked successfully";
    }

    public String edit(MarkDto markDto, Long id) {
        if (!repo.existsById(id)) return "There is no selected mark";
        if (markDto.getBall() > 5 && markDto.getBall() < 1) return "Your ball isn't correct";
        if (!studentRepo.existsById(markDto.getStudent_id())) return "There is no Student with that id";
        if (!teacherRepo.existsById(markDto.getTeacher_id())) return "There is no Teacher with that id";
        if (!subjectRepo.existsByName(markDto.getSubject_name())) return "There is no Subject";
        repo.save(new Mark(id,
                markDto.getBall(),
                getStudent(markDto.getStudent_id()),
                getTeacher(markDto.getTeacher_id()),
                getSubject(markDto.getSubject_name()),
                getDate())
        );
        return "Your Mark has edited successfully";
    }

    public String delete(Long id) {
        if (!repo.existsById(id)) return "There is no selected mark";
        repo.deleteById(id);
        return "Mark successfully deleted";
    }
    
    private Student getStudent(long id){
        return studentRepo.getById(id);
    }

    private Teacher getTeacher(long id){
        return teacherRepo.getById(id);
    }
    
    private Subject getSubject(String name){
        return subjectRepo.findByName(name).get();
    }
    
    private TimeTable getDate(){
        return timeTableRepo.findByKey(getNowTime()).get();
    }

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
