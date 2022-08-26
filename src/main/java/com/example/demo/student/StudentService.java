package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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


    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentsByEmail(student.getEmail());

        if (studentOptional.isPresent()){
            throw new IllegalStateException("email was taken");
        }

        studentRepository.save(student);

        System.out.println(student);
    }

    //i don't have to implement any jpql
    //so entity goes into a managed  state
    @Transactional
    public void updateStudent(Long studentId, String studentName, String studentEmail){

        //finds student with this id, if there is no one student with this id - throws exception
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does mot exists"));

        //checks if new name is not null,   it's length bigger than 0    and does equal previous
        if (studentName != null &&
                studentName.length() > 0 &&
                !Objects.equals(student.getName(), studentName)) {

            student.setName(studentName);
        }

        //checks if new email is not null,   it's length bigger than 0    and does equal previous
        if (studentEmail != null &&
                studentEmail.length() > 0 &&
                !Objects.equals(student.getEmail(), studentEmail)) {

            //creating new student variable by his email just to check if this email was taken
            Optional<Student> studentOptional = studentRepository.findStudentsByEmail(studentEmail);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("email was taken");     //if student with this email exists - method will stop
            }                                                           //else - continue

            student.setEmail(studentEmail);
        }
    }

    public void deleteStudent(Long studentId) {

        boolean exists = studentRepository.existsById(studentId);

        if (!exists){
            throw new IllegalStateException("student with id " + studentId + " does not exists");
        }

        studentRepository.deleteById(studentId);
    }
}


