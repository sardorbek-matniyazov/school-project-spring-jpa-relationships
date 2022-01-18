package com.example.scholar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScholarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScholarApplication.class, args);
    }
    /*
    * Hello buddy! I,m going to show you some Spring boot application's means, which is
    *  Spring-Data-Jpa in this project.
    * This project's goal is creating CRUD by Data base relationships with hibernate.
    * My chosen database is school.
    * Ok. Let's try understanding.
    * If you want to use this project, you must change 'application.properties' file(this includes URL,
    * username, password).
    * */
}
