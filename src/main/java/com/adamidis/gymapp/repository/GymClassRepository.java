package com.adamidis.gymapp.repository;

import com.adamidis.gymapp.model.Difficulty;
import com.adamidis.gymapp.model.GymClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GymClassRepository extends JpaRepository<GymClass, Long> {

    List<GymClass> findByDifficulty(Difficulty difficulty); // Custom query method to find gym classes by difficulty level

    List<GymClass> findByInstructorName(String instructorName); // Custom query method to find gym classes by instructor name

    List<GymClass> findByClassNameContainingIgnoreCase(String className); // Custom query method to search gym classes by name (case-insensitive)

//    List<GymClass> findByStartTimeAfterOrderByStartTimeAsc(LocalDateTime now);

    List<GymClass> findByInstructorNameContainingIgnoreCase(String instructorName);
}
