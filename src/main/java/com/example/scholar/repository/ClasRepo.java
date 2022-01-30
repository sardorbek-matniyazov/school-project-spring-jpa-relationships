package com.example.scholar.repository;


import com.example.scholar.entity.Clas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClasRepo extends JpaRepository<Clas, Long> {

    Clas findById(long id);

    boolean existsByName(String name);

    boolean existsByNameAndSchoolNameAndSchoolAddress(String name, String school_name, String address);

    Optional<Clas> findByNameAndSchoolNameAndSchoolAddress(String name, String school_name, String address);

    Optional<Clas> findBySchoolId(Long id);
}
