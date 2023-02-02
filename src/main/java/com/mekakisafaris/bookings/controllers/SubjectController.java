package com.mekakisafaris.bookings.controllers;
import com.mekakisafaris.bookings.entity.Subjects;
import com.mekakisafaris.bookings.repositories.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/subjects")
public class SubjectController {
    private SubjectRepo subjectRepo;

    //Post mapping for adding single subject
    @PostMapping("/new")
    public ResponseEntity<Subjects> addSubject(@RequestBody Subjects subjects) {
        Optional<Subjects>savedSubject= subjectRepo.findById(subjects.getSubjectcode());
        if (savedSubject.isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(subjectRepo.save(subjects), HttpStatus.CREATED);
    }

    //Post mapping for adding list of subjects
    @PostMapping("/list")
    public ResponseEntity<List<Subjects>> addSubjects(@RequestBody List<Subjects> subjects) {
       List<Subjects> savedSubjects=new ArrayList<>();
        for (Subjects subject:subjects) {
            Optional<Subjects> subjectOptional=subjectRepo.findById(subject.getSubjectcode());
            if (!subjectOptional.isPresent()){
                new ResponseEntity<>(subjects, HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>(subjects, HttpStatus.CREATED);
    }

    //Update mapping for updating subject by subject code
    @PutMapping("/{subjectcode}")
    public ResponseEntity<Subjects> updateSubject(@PathVariable int subjectcode, @RequestBody Subjects subjects) {
        Optional<Subjects> existingSubject = subjectRepo.findById(subjectcode);
        if (!existingSubject.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        subjects.setSubjectcode(subjectcode);
        Subjects updatedSubject = subjectRepo.save(subjects);
        return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
    }

    //Delete mapping for deleting subject by subject code
    @DeleteMapping("/{subjectcode}")
    public ResponseEntity<Subjects> deleteSubject(@PathVariable int subjectcode) {
        Optional<Subjects> existingSubject = subjectRepo.findById(subjectcode);
        if (!existingSubject.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        subjectRepo.deleteById(subjectcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Get mapping for finding all subjects
    @GetMapping("/findall")
    public ResponseEntity<List<Subjects>> findAllSubjects() {
        List<Subjects> subjects = subjectRepo.findAll();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    //Get mapping for finding subject by subject code
    @GetMapping("/get/{subjectcode}")
    public ResponseEntity<Subjects> findSubjectById(@PathVariable int subjectcode) {
        Optional<Subjects> existingSubject = subjectRepo.findById(subjectcode);
        if (!existingSubject.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(existingSubject.get(), HttpStatus.OK);
    }
}
