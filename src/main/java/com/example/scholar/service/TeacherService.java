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
import java.util.Optional;

@Service
public class TeacherService {

    // these repositories connect to database tables with hibernate and simplifies our work.
    // you can find it out by studying the wider hibernate
    private final TeacherRepo repo;
    private final SchoolRepo schoolRepo;
    private final SubjectRepo subjectRepo;
    private final MarkRepo markRepo;

    private School school;
    private Subject subject;

    // dependency injection
    public TeacherService(TeacherRepo repo, SchoolRepo schoolRepo, SubjectRepo subjectRepo, MarkRepo markRepo) {
        this.repo = repo;
        this.schoolRepo = schoolRepo;
        this.subjectRepo = subjectRepo;
        this.markRepo = markRepo;
    }

    // getting all exists teachers from database
    public List<Teacher> getAll(){
        return repo.findAll();
    }

    // getting teacher, which exists with id from database
    public Teacher get(Long id) {
        return repo.findById(id).isEmpty() ? null : repo.findById(id).get();
    }

    // saving new teacher
    public String save(TeacherDto teacherDto) {

        // saving new teacher. why unconditional, because there may be people with the same name
        repo.save(new Teacher(teacherDto.getFirst_name(),
                teacherDto.getLast_name(),
                getSchool(teacherDto),
                getSubject(teacherDto))
        );

        return "Teacher successfully added";
    }

    // editing the teacher
    public String putOne(TeacherDto teacherDto, Long id) {

        // if there is no teacher with the selected id in the databases
        if (repo.findById(id).isEmpty())
            return "There is no selected teacher";

        // editing new teacher. why unconditional, because there may be people with the same name
        repo.save(new Teacher(id,
                teacherDto.getFirst_name(),
                teacherDto.getLast_name(),
                getSchool(teacherDto),
                getSubject(teacherDto)));

        return "Teacher Successfully edited";
    }

    // deleting selected teacher
    public String deleteOne(Long id) {

        // if there is no teacher with the selected id in the databases
        if (repo.findById(id).isEmpty())return "There is no selected teacher";

        deleteFromMark(id);

        repo.delete(repo.getById(id));
        return "Teacher successfully deleted !";
    }

    // this is magic :)
    private void deleteFromMark(Long id) {

        // creating "deleted" teacher
        if (!repo.existsBySchoolName("deleted")){
            if (!subjectRepo.existsByName("anything"))
                subjectRepo.save(new Subject("anything"));

            if (!schoolRepo.existsByNameAndAddress("deleted", "anywhere"))
                schoolRepo.save(new School("deleted", "anywhere"));

            repo.save(new Teacher(
                    "Deleted user",
                    "anybody",
                    schoolRepo.findByNameAndAddress("deleted", "anywhere").get(),
                    subjectRepo.findByName("anything").get()));
        }

        // this changes name of deleted teacher's grades with "deleted"
        for (Mark mark: markRepo.findAll()) {
            if (mark.getTeacher().getId() == id){
                markRepo.save(new Mark(
                        mark.getId(),
                        mark.getBall(),
                        mark.getStudent(),
                        repo.findBySchoolName("deleted").get(),
                        mark.getSubject(),
                        mark.getTime())
                );
            }
        }
    }

    // getting school
    private School getSchool(TeacherDto teacherDto){

        // checking with hibernate
        // getting
        if(schoolRepo.findByNameAndAddress(teacherDto.getName(), teacherDto.getAddress()).isPresent())
            return schoolRepo.findByNameAndAddress(teacherDto.getName(), teacherDto.getAddress()).get();

        // if there is no school with the selected id in the databases, creating new
        school = new School(teacherDto.getName(), teacherDto.getAddress());
        schoolRepo.save(school);
        return getSchool(teacherDto);
    }

    // getting subject
    private Subject getSubject(TeacherDto teacherDto){

        // checking without hibernate
        // getting
        for (Subject subject: subjectRepo.findAll()) {
            if (subject.getName().equals(teacherDto.getName_s()))return subject;
        }

        // if there is no subject with the selected id in the databases, creating new
        subject = new Subject(teacherDto.getName_s());
        subjectRepo.save(subject);
        return getSubject(teacherDto);
    }

}
