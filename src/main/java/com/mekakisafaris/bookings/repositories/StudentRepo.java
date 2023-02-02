package com.mekakisafaris.bookings.repositories;

import com.mekakisafaris.bookings.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Integer> {
}
