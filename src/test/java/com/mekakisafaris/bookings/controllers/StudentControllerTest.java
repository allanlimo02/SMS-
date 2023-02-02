package com.mekakisafaris.bookings.controllers;

import com.mekakisafaris.bookings.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class StudentControllerTest {
    @Autowired
    StudentController studentController;

    @BeforeEach
    void setUp() {
        Student testStudent=new Student(1,"Allan","Ngetich","Limo","allaney@gmail.com","2023-01-20");
    }

    @Test
    void addStudent() {


    }

    @Test
    void deleteStudent() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void addStudents() {
    }

    @Test
    void getall() {
    }
}