package com.adamidis.gymapp.dto;

import com.adamidis.gymapp.model.Booking;
import com.adamidis.gymapp.model.BookingStatus;

import java.time.LocalDateTime;

public class BookingResponse {

    private Long id;
    private Long userId;
    private String username;
    private Long classId;
    private String className;
    private String instructorName;
    private String schedule;
    private LocalDateTime bookingDate;
    private BookingStatus status;
    private LocalDateTime createdAt;

    // Constructor
    public BookingResponse(Booking booking) {
        this.id = booking.getId();
        this.userId = booking.getUser().getId();
        this.username = booking.getUser().getUsername();
        this.classId = booking.getGymClass().getId();
        this.className = booking.getGymClass().getClassName();
        this.instructorName = booking.getGymClass().getInstructorName();
        this.schedule = booking.getGymClass().getSchedule();
        this.bookingDate = booking.getBookingDate();
        this.status = booking.getStatus();
        this.createdAt = booking.getCreatedAt();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
