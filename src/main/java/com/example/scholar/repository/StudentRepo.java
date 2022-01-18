package com.example.scholar.repository;

import com.example.scholar.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    @Override
    boolean existsById(Long id);
}
