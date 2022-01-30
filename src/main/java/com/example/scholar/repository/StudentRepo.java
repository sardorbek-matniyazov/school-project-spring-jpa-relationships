package com.example.scholar.repository;

import com.example.scholar.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    @Override
    boolean existsById(Long id);

    Optional<Student> findBySchoolId(Long id);

    Optional<Student> findByClasId(Long id);
}
