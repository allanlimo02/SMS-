package com.mekakisafaris.bookings.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    private int admNo;
    private String firstname;
    private String middlename;
    private String lastname;
    private String email=firstname+"."+lastname+admNo+"@kbhs.ac.ke";
    private String admissiondate;
}
