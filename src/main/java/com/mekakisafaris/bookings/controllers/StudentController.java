package com.mekakisafaris.bookings.controllers;

import com.mekakisafaris.bookings.entity.Student;
import com.mekakisafaris.bookings.repositories.StudentRepo;
import com.mekakisafaris.bookings.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    StudentService studentService;
    // new student
    @PostMapping("/new")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Optional<Student> existingStudent = studentRepo.findById(student.getAdmNo());
        if (existingStudent.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        student.setEmail(student.getFirstname() + "." + student.getLastname() + student.getAdmNo() + "@kbhs.ac.ke");
        Student savedStudent = studentRepo.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }
    // Delete Mapping
    @DeleteMapping("/student/{admNo}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int admNo) {
        Optional<Student> student = studentRepo.findById(admNo);
        if (!student.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentRepo.deleteById(admNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    // update
    @PutMapping("/student/{admNo}")
    public ResponseEntity<Student> updateStudent(@PathVariable int admNo, @RequestBody Student student) {
        Optional<Student> existingStudent = studentRepo.findById(admNo);
        if (!existingStudent.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingStudent.get().setFirstname(student.getFirstname());
        existingStudent.get().setMiddlename(student.getMiddlename());
        existingStudent.get().setLastname(student.getLastname());
        existingStudent.get().setEmail(student.getEmail());
        Student updatedStudent = studentRepo.save(existingStudent.get());
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }
// Addd list of students
@PostMapping("/students")
public ResponseEntity<List<Student>> addStudents(@RequestBody List<Student> students) {
    List<Student> savedStudents = new ArrayList<>();
    for (Student student : students) {
        Optional<Student> existingStudent = studentRepo.findById(student.getAdmNo());
        if (!existingStudent.isPresent()) {
            changeemail(student);
            savedStudents.add(studentRepo.save(student));
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    return new ResponseEntity<>(savedStudents, HttpStatus.CREATED);
}
// Read
    @GetMapping("all")
public List<Student> getall(){
        return studentRepo.findAll();
    }
    //setting email
    void changeemail (Student student){
        student.setEmail(student.getFirstname()+"."+student.getLastname()+student.getAdmNo()+"@kbhs.ac.ke");
    }

}
