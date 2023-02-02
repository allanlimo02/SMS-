package com.mekakisafaris.bookings.repositories;

import com.mekakisafaris.bookings.entity.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepo extends JpaRepository<Subjects,Integer> {
}
