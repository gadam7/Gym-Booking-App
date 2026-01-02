package com.adamidis.gymapp.dto;

import jakarta.validation.constraints.NotNull;

public class BookingRequest {

    @NotNull(message = "Class ID is required")
    private Long classId;

    // Constructors
    public BookingRequest() {

    }

    public BookingRequest(Long classId) {
        this.classId = classId;
    }

    // Getters and Setters
    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }
}
