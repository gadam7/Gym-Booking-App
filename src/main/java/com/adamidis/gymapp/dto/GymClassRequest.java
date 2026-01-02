package com.adamidis.gymapp.dto;

import com.adamidis.gymapp.model.Difficulty;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class GymClassRequest {

    @NotBlank(message = "Class name is required")
    @Size(max = 100)
    private String className;

    private String description;

    @NotBlank(message = "Instructor name is required")
    @Size(max = 100)
    private String instructorName;

    @NotNull(message = "Difficulty level is required")
    private Difficulty difficulty;

    @NotNull(message = "Start time is required")
    private String schedule;

    @NotNull(message = "End time is required")
    private Integer duration;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 12, message = "Capacity must not exceed 12")
    private Integer capacity;

    // Constructors
    public GymClassRequest() {

    }

    public GymClassRequest(String className, String description, String instructorName, Difficulty difficulty, String schedule, Integer duration, Integer capacity) {
        this.className = className;
        this.description = description;
        this.instructorName = instructorName;
        this.difficulty = difficulty;
        this.schedule = schedule;
        this.duration = duration;
        this.capacity = capacity;
    }

    // Getters and Setters

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

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
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
}
