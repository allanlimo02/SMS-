package com.mekakisafaris.bookings.repositories;

import com.mekakisafaris.bookings.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher,Integer> {
}
