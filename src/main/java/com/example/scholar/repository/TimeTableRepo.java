package com.example.scholar.repository;

import com.example.scholar.entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimeTableRepo extends JpaRepository<TimeTable, Long> {
    Optional<TimeTable> findByKey(String key);
}
