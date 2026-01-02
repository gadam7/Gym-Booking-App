package com.adamidis.gymapp.controller;

import com.adamidis.gymapp.dto.GymClassRequest;
import com.adamidis.gymapp.dto.GymClassResponse;
import com.adamidis.gymapp.dto.MessageResponse;
import com.adamidis.gymapp.model.GymClass;
import com.adamidis.gymapp.service.GymClassService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "http://localhost:4200")
public class GymClassController {

    @Autowired
    private GymClassService gymClassService;

    // Get all classes (PUBLIC)
    @GetMapping
    public ResponseEntity<List<GymClassResponse>> getAllClasses() {
        List<GymClass> classes = gymClassService.getAllClasses();
        List<GymClassResponse> response = classes.stream()
                .map(GymClassResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // Get class by ID (PUBLIC)
    @GetMapping("/{id}")
    public ResponseEntity<GymClassResponse> getClassById(@PathVariable Long id) {
        try {
            GymClass gymClass = gymClassService.getClassById(id);
            return ResponseEntity.ok(new GymClassResponse(gymClass));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Get classes by instructor (PUBLIC)
    @GetMapping("/instructor/{instructorName}")
    public ResponseEntity<List<GymClassResponse>> getClassesByInstructor(@PathVariable String instructorName) {
        List<GymClass> classes = gymClassService.getClassesByInstructor(instructorName);
        List<GymClassResponse> response = classes.stream()
                .map(GymClassResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // Create class (ADMIN ONLY)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createClass(@Valid @RequestBody GymClassRequest request) {
        try {
            GymClass gymClass = new GymClass();
            gymClass.setClassName(request.getClassName());
            gymClass.setDescription(request.getDescription());
            gymClass.setInstructorName(request.getInstructorName());
            gymClass.setDifficulty(request.getDifficulty());
            gymClass.setSchedule(request.getSchedule());
            gymClass.setDuration(request.getDuration());
            gymClass.setCapacity(request.getCapacity());

            GymClass createdClass = gymClassService.createClass(gymClass);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new GymClassResponse(createdClass));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    . body(new MessageResponse(e.getMessage()));
        }
    }

    // Update class (ADMIN ONLY)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateClass(@PathVariable Long id, @Valid @RequestBody GymClassRequest request) {
        try {
            GymClass gymClass = new GymClass();
            gymClass.setClassName(request.getClassName());
            gymClass.setDescription(request. getDescription());
            gymClass.setInstructorName(request. getInstructorName());
            gymClass.setDifficulty(request.getDifficulty());
            gymClass.setSchedule(request.getSchedule());
            gymClass.setDuration(request.getDuration());
            gymClass.setCapacity(request.getCapacity());

            GymClass updatedClass = gymClassService.updateClass(id, gymClass);
            return ResponseEntity.ok(new GymClassResponse(updatedClass));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse(e.getMessage()));
        }
    }

    // Delete class (ADMIN ONLY)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<? > deleteClass(@PathVariable Long id) {
        try {
            gymClassService.deleteClass(id);
            return ResponseEntity.ok(new MessageResponse("Class deleted successfully! "));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse(e.getMessage()));
        }
    }
}