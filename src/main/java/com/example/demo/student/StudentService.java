package com.example.demo.student;

import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class StudentService {

    @GetMapping //to get something out from our server
    public List<Student> getStudents(){
        return List.of(
                new Student (
                        1L,
                        "Maksym",
                        "maksym@gmail.com",
                        LocalDate.of(2004, Month.MAY, 13),
                        18)
        );
    }
}
