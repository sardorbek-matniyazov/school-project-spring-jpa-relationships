package com.example.scholar.repository;

import com.example.scholar.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepo extends JpaRepository<School, Long> {
    public School findById(long id);
}
