package com.example.scholar.repository;

import com.example.scholar.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepo extends JpaRepository<Subject, Long> {
    Optional<Subject> findByName(String name);

    boolean existsByName(String name);

}
