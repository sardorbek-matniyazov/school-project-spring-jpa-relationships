package com.example.scholar.service;

import com.example.scholar.dto.TeacherDto;
import com.example.scholar.entity.Mark;
import com.example.scholar.entity.School;
import com.example.scholar.entity.Subject;
import com.example.scholar.entity.Teacher;
import com.example.scholar.repository.MarkRepo;
import com.example.scholar.repository.SchoolRepo;
import com.example.scholar.repository.SubjectRepo;
import com.example.scholar.repository.TeacherRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepo repo;
    private final SchoolRepo schoolRepo;
    private final SubjectRepo subjectRepo;
    private final MarkRepo markRepo;

    private School school;
    private Subject subject;
    private Teacher teacher;

    public TeacherService(TeacherRepo repo, SchoolRepo schoolRepo, SubjectRepo subjectRepo, MarkRepo markRepo) {
        this.repo = repo;
        this.schoolRepo = schoolRepo;
        this.subjectRepo = subjectRepo;
        this.markRepo = markRepo;
    }

    public List<Teacher> getAll(){
        return repo.findAll();
    }

    public Teacher get(Long id) {
        return repo.findById(id).isEmpty() ? null : repo.findById(id).get();
    }

    public String save(TeacherDto teacherDto) {
        teacher = new Teacher(teacherDto.getFirst_name(), teacherDto.getLast_name(),
                getSchool(teacherDto),
                getSubject(teacherDto));
        repo.save(teacher);
        return "Teacher successfully added";
    }

    public String putOne(TeacherDto teacherDto, Long id) {
        if (repo.findById(id).isEmpty())return "There is no selected teacher";
        teacher = new Teacher(id, teacherDto.getFirst_name(), teacherDto.getLast_name(),
                getSchool(teacherDto), getSubject(teacherDto));
        repo.save(teacher);
        return "Teacher Successfully edited";
    }

    public String deleteOne(Long id) {
        if (repo.findById(id).isEmpty())return "There is no selected teacher";
        deleteFromMark(id);
        repo.delete(repo.getById(id));
        return "Teacher successfully deleted !";
    }

    private void deleteFromMark(Long id) {
        for (Mark mark: markRepo.findAll()) {
            if (mark.getTeacher().getId() == id){
                mark = new Mark(
                        mark.getId(),
                        mark.getBall(),
                        mark.getStudent(),
                        repo.getById(0L),
                        mark.getSubject(),
                        mark.getTime()
                );
                markRepo.save(mark);
            }
        }
    }

    private School getSchool(TeacherDto teacherDto){
        for (School school: schoolRepo.findAll()) {
            if (school.getName().equals(teacherDto.getName())
                    && school.getAddress().equals(teacherDto.getAddress()))return school;
        }
        school = new School(teacherDto.getName(), teacherDto.getAddress());
        schoolRepo.save(school);
        return getSchool(teacherDto);
    }

    private Subject getSubject(TeacherDto teacherDto){
        for (Subject subject: subjectRepo.findAll()) {
            if (subject.getName().equals(teacherDto.getName_s()))return subject;
        }
        subject = new Subject(teacherDto.getName_s());
        subjectRepo.save(subject);
        return getSubject(teacherDto);
    }

}
