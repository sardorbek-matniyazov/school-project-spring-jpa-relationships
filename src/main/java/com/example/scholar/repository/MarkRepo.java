package com.example.scholar.repository;

import com.example.scholar.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepo extends JpaRepository<Mark, Long> {
    @Override
    boolean existsById(Long id);


}
