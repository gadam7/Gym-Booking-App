package com.adamidis.gymapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
/* Define the "users" table with a unique constraint on the "email" column */
@Table(name = "users",
        uniqueConstraints = {@UniqueConstraint(columnNames = "email")}) // Ensure email uniqueness at the database level
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the primary key using the IDENTITY strategy
    private Long id;

    @NotBlank // Ensure the "username" field is not blank
    @Size(max = 50) // Limit the "username" field to a maximum of 50 characters
    @Column(nullable = false) // Define the "username" column as not nullable
    private String username;

    @NotBlank
    @Email
    @Size(max = 100)
    @Column(nullable = false, unique = true) // Define the "email" column as not nullable and unique
    private String email;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false) // Define the "password" column as not nullable
    private String password;

    @Enumerated(EnumType.STRING) // Store the enum value as a string in the database
    @Column(nullable = false)
    private Role role = Role.USER; // Default role is USER

    @CreationTimestamp // Automatically set the creation timestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user",
                cascade =  CascadeType.ALL, orphanRemoval = true) // One-to-many relationship with Booking entity
    private Set<Booking> bookings = new HashSet<>();

    // Constructors
    public User() {

    }

    public User(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", createdAt=" + createdAt +
                ", bookings=" + bookings +
                '}';
    }
}
