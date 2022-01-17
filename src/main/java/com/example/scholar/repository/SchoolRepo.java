package com.example.scholar.repository;

import com.example.scholar.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepo extends JpaRepository<School, Long> {
    public School findById(long id);

    Optional<School> findByNameAndAddress(String Name, String address);

    boolean existsByNameAndAddress(String name, String address);
}
