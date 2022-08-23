package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service //like @Component but better
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @GetMapping //to get something out from our server
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }
}

//new Student (
//                        1L,
//                        "Maksym",
//                        "maksym@gmail.com",
//                        LocalDate.of(2004, Month.MAY, 13),
//                        18)