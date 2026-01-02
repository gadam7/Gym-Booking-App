package com.adamidis.gymapp.service;

import com.adamidis.gymapp.model.Difficulty;
import com.adamidis.gymapp.model.GymClass;
import com.adamidis.gymapp.repository.GymClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GymClassService {

    @Autowired
    private GymClassRepository gymClassRepository;

    // Get all gym classes
    public List<GymClass> getAllClasses() {
        return gymClassRepository.findAll();
    }

    // Get gym class by ID
    public GymClass getClassById(Long id) {
        return gymClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gym class not found with id: " + id));
    }

    // Get classes by instructor name
    public List<GymClass> getClassesByInstructor(String instructorName) {
        return gymClassRepository.findByInstructorNameContainingIgnoreCase(instructorName);
    }

    // Get classes by difficulty level
    public List<GymClass> getClassesByDifficulty(Difficulty difficulty) {
        return gymClassRepository.findByDifficulty(difficulty);
    }

    // Search classes by name
    public List<GymClass> searchClassesByName(String className) {
        return gymClassRepository.findByClassNameContainingIgnoreCase(className);
    }

    // Create new class (ADMIN only)
    public GymClass createClass(GymClass gymClass) {
        // Validation: Check capacity
        if (gymClass.getCapacity() <= 0) {
            throw new RuntimeException("Capacity must be greater than 0!");
        }

        // Set initial available spots
        gymClass.setAvailableSpots(gymClass.getCapacity());

        return gymClassRepository.save(gymClass);
    }

    // Update class (ADMIN only)
    public GymClass updateClass(Long id, GymClass updatedClass) {
        GymClass existingClass = getClassById(id);

        // Update fields
        existingClass.setClassName(updatedClass.getClassName());
        existingClass.setDescription(updatedClass.getDescription());
        existingClass.setInstructorName(updatedClass.getInstructorName());
        existingClass.setSchedule(updatedClass.getSchedule());
        existingClass.setDuration(updatedClass.getDuration());
        existingClass.setDifficulty(updatedClass.getDifficulty());

        // Update capacity (but maintain booking count)
        int bookedSpots = existingClass.getCapacity() - existingClass.getAvailableSpots();
        if (updatedClass.getCapacity() < bookedSpots) {
            throw new RuntimeException("Cannot reduce capacity below current bookings!");
        }

        existingClass.setCapacity(updatedClass.getCapacity());
        existingClass.setAvailableSpots(updatedClass.getCapacity() - bookedSpots);

        return gymClassRepository.save(existingClass);
    }

    // Delete class (ADMIN only)
    public void deleteClass(Long id) {
        GymClass gymClass = getClassById(id);

        // Check if class has bookings
        int bookedSpots = gymClass.getCapacity() - gymClass.getAvailableSpots();
        if (bookedSpots > 0) {
            throw new RuntimeException("Cannot delete class with existing bookings!");
        }

        gymClassRepository.delete(gymClass);
    }

    // Decrease available spots (called when booking)
    public void decreaseAvailableSpots(Long classId) {
        GymClass gymClass = getClassById(classId);

        if (gymClass.getAvailableSpots() <= 0) {
            throw new RuntimeException("Class is fully booked!");
        }

        gymClass.setAvailableSpots(gymClass.getAvailableSpots() - 1);
        gymClassRepository.save(gymClass);
    }

    // Increase available spots (called when canceling booking)
    public void increaseAvailableSpots(Long classId) {
        GymClass gymClass = getClassById(classId);

        if (gymClass.getAvailableSpots() >= gymClass.getCapacity()) {
            throw new RuntimeException("Available spots cannot exceed capacity!");
        }

        gymClass.setAvailableSpots(gymClass.getAvailableSpots() + 1);
        gymClassRepository.save(gymClass);
    }

    // Check if class is available for booking
    public boolean isClassAvailable(Long classId) {
        GymClass gymClass = getClassById(classId);
        return gymClass.getAvailableSpots() > 0;
    }
}
