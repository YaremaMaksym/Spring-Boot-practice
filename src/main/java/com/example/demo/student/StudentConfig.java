package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student maksym = new Student (
                    "Maksym",
                    "maksym@gmail.com",
                    LocalDate.of(2004, Month.MAY, 13)
            );

            Student alex = new Student (
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2005, Month.APRIL, 12)
            );

            repository.saveAll(List.of(maksym, alex));
        };
    }
}
