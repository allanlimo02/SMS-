package com.mekakisafaris.bookings.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Reference;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    private int teacherId;
    private String teachername;
    private String teacheremail;
    private String department;
//    @OneToOne @JoinColumn(referencedColumnName = "teachersubjects")
//    private List<Departments> departments;
//    @OneToMany
//    private List<Subjects> subjectsList;


}
