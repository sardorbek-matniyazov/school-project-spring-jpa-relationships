package com.example.scholar.repository;


import com.example.scholar.entity.Clas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClasRepo extends JpaRepository<Clas, Long> {
    Clas findById(long id);

    boolean existsByName(String name);

    Optional<Clas> findByName(String name);
}
