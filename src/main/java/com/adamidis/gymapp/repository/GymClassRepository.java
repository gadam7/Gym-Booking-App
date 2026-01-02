package com.adamidis.gymapp.repository;

import com.adamidis.gymapp.model.Difficulty;
import com.adamidis.gymapp.model.GymClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GymClassRepository extends JpaRepository<GymClass, Long> {

    List<GymClass> findByDifficulty(Difficulty difficulty); // Custom query method to find gym classes by difficulty level

    List<GymClass> findByInstructor(String instructor); // Custom query method to find gym classes by instructor name

    List<GymClass> findByNameContainingIgnoreCase(String name); // Custom query method to search gym classes by name (case-insensitive)
}
