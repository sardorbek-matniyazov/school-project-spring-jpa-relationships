package com.example.scholar.repository;

import com.example.scholar.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {
    boolean existsById(Long id);

    boolean existsBySchoolName(String name);

    Optional<Teacher> findBySchoolName(String s);

    Optional<Teacher> findBySchoolId(long id);
}