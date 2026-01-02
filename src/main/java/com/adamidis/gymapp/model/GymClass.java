package com.adamidis.gymapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "gym_classes")
public class GymClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String className;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String instructorName;

    @Min(1) // Minimum duration of 1 minute
    @Column(nullable = false)
    private Integer duration; // Duration in minutes

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String schedule; // e.g., "Mondays and Wednesdays at 06:00 PM"

    @Min(1)
    @Max(12) // Assuming a maximum capacity of 12 participants
    @Column(nullable = false)
    private Integer capacity;

    // NEW FIELD - Track available spots
    @Column(nullable = false)
    private Integer availableSpots;

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "gymClass",
                cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Booking> booking = new HashSet<>();

    // Constructors
    public GymClass() {

    }

    public GymClass(String className, String instructorName, Integer duration, Difficulty difficulty,
                    String description, String schedule, Integer capacity) {
        this.className = className;
        this.instructorName = instructorName;
        this.duration = duration;
        this.difficulty = difficulty;
        this.description = description;
        this.schedule = schedule;
        this.capacity = capacity;
        this.availableSpots = capacity; // Initialize available spots to capacity
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Booking> getBooking() {
        return booking;
    }

    public void setBooking(Set<Booking> booking) {
        this.booking = booking;
    }

    public Integer getAvailableSpots() {
        return availableSpots;
    }

    public void setAvailableSpots(Integer availableSpots) {
        this.availableSpots = availableSpots;
    }

    @Override
    public String toString() {
        return "GymClass{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", duration=" + duration +
                ", difficulty=" + difficulty +
                ", description='" + description + '\'' +
                ", schedule='" + schedule + '\'' +
                ", capacity=" + capacity +
                ", availableSpots=" + availableSpots +
                ", createdAt=" + createdAt +
                ", booking=" + booking +
                '}';
    }
}
