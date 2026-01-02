package com.adamidis.gymapp.dto;

import com.adamidis.gymapp.model.Difficulty;
import com.adamidis.gymapp.model.GymClass;

import java.time.LocalDateTime;

public class GymClassResponse {

    private Long id;
    private String className;
    private String description;
    private String instructorName;
    private Difficulty difficulty;
    private String schedule;
    private Integer duration;
    private Integer capacity;
    private Integer availableSpots;
    private LocalDateTime createdAt;
    private boolean isAvailable;

    // Constructor from GymClass entity
    public GymClassResponse(GymClass gymClass) {
        this.id = gymClass. getId();
        this.className = gymClass.getClassName();
        this.description = gymClass.getDescription();
        this.instructorName = gymClass.getInstructorName();
        this.difficulty = gymClass.getDifficulty();
        this.schedule = gymClass.getSchedule();
        this.duration = gymClass.getDuration();
        this.capacity = gymClass. getCapacity();
        this.availableSpots = gymClass. getAvailableSpots();
        this.createdAt = gymClass.getCreatedAt();
        this.isAvailable = gymClass.getAvailableSpots() > 0;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getAvailableSpots() {
        return availableSpots;
    }

    public void setAvailableSpots(Integer availableSpots) {
        this.availableSpots = availableSpots;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}