package com.adamidis.gymapp.repository;

import com.adamidis.gymapp.model.Booking;
import com.adamidis.gymapp.model.BookingStatus;
import com.adamidis.gymapp.model.GymClass;
import com.adamidis.gymapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Find all bookings by user
    List<Booking> findByUser(User user); // Custom query method to find bookings by user

    // Find all bookings by user and status
    List<Booking> findByUserAndStatus(User user, BookingStatus status); // Custom query method to find bookings by user and status

    // Find all bookings by gym class
    List<Booking> findByGymClass(GymClass gymClass); // Custom query method to find bookings by gym class

    // Find all bookings by gym class and status
    List<Booking> findByGymClassAndStatus(GymClass gymClass, BookingStatus status); // Custom query method to find bookings by gym class and status

    // Check if user already booked a specific class
    Optional<Booking> findByUserAndGymClassAndStatus(User user, GymClass gymClass, BookingStatus status); // Custom query method to find a booking by user, gym class, and status

    // Check if a booking exists by user, gym class, and status
    boolean existsByUserAndGymClassAndStatus(User user, GymClass gymClass, BookingStatus status); // Check if a booking exists by user, gym class, and status

    long countByGymClassAndStatus(GymClass gymClass, BookingStatus status); // Count bookings by gym class and status
}

