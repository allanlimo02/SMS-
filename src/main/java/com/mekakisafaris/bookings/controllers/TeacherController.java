package com.mekakisafaris.bookings.controllers;

import com.mekakisafaris.bookings.entity.Teacher;
import com.mekakisafaris.bookings.repositories.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    TeacherRepo teacherRepo;
    // new teacher
    @PostMapping("/teacher")
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
        Optional<Teacher> existingTeacher = teacherRepo.findById(teacher.getTeacherId());
        if (existingTeacher.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Teacher savedTeacher = teacherRepo.save(teacher);
        return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
    }
    //Update mapping
    @PutMapping("/{teacherId}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable int teacherId, @RequestBody Teacher teacher) {
        Optional<Teacher> existingTeacher = teacherRepo.findById(teacherId);
        if (!existingTeacher.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            Teacher savedTeacher = teacherRepo.save(teacher);
            return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);

        }
        existingTeacher.get().setTeachername(teacher.getTeachername());
        existingTeacher.get().setTeacheremail(teacher.getTeacheremail());
        existingTeacher.get().setDepartment(teacher.getDepartment());
        Teacher updatedTeacher = teacherRepo.save(existingTeacher.get());
        return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
    }

    //Get mapping
    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherRepo.findAll();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    //Get by id mapping
    @GetMapping("/{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable int teacherId) {
        Optional<Teacher> teacher = teacherRepo.findById(teacherId);
        if (!teacher.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teacher.get(), HttpStatus.OK);
    }

    //Delete mapping
    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable int teacherId) {
        Optional<Teacher> teacher = teacherRepo.findById(teacherId);
        if (!teacher.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        teacherRepo.deleteById(teacherId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
