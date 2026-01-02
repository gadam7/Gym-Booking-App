package com.adamidis.gymapp.service;

import com.adamidis.gymapp.model.Booking;
import com.adamidis.gymapp.model.BookingStatus;
import com.adamidis.gymapp.model.GymClass;
import com.adamidis.gymapp.model.User;
import com.adamidis.gymapp.repository.BookingRepository;
import com.adamidis.gymapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GymClassService gymClassService;

    // get all bookings (ADMIN only)
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get booking by ID
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
    }

    // Get user's bookings
    public List<Booking> getUserBookings(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return bookingRepository.findByUser(user);
    }

    // Get user's active bookings (CONFIRMED only)
    public List<Booking> getUserActiveBookings(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return bookingRepository.findByUserAndStatus(user, BookingStatus.CONFIRMED);
    }

    // Get bookings for a specific class (ADMIN only)
    public List<Booking> getClassBookings(Long classId) {
        GymClass gymClass = gymClassService.getClassById(classId);
        return bookingRepository.findByGymClass(gymClass);
    }

    // Create booking
    @Transactional
    public Booking createBooking(Long userId, Long classId) {
        // Get user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found! "));

        // Get class
        GymClass gymClass = gymClassService.getClassById(classId);

        // Validation 1: Check if class has available spots
        if (!gymClassService.isClassAvailable(classId)) {
            throw new RuntimeException("Class is fully booked!");
        }

        // Validation 2: Check if user already booked this class
        if (bookingRepository.existsByUserAndGymClassAndStatus(user, gymClass, BookingStatus.CONFIRMED)) {
            throw new RuntimeException("You have already booked this class!");
        }

        // Decrease available spots
        gymClassService.decreaseAvailableSpots(classId);

        // Create booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setGymClass(gymClass);
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus(BookingStatus. CONFIRMED);

        return bookingRepository.save(booking);
    }

    // Cancel booking
    @Transactional
    public Booking cancelBooking(Long bookingId, Long userId) {
        Booking booking = getBookingById(bookingId);

        // Validation 1: Check if booking belongs to user
        if (!booking.getUser().getId().equals(userId)) {
            throw new RuntimeException("You can only cancel your own bookings!");
        }

        // Validation 2: Check if booking is already cancelled
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new RuntimeException("Booking is already cancelled!");
        }

        // Update booking status
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        // Increase available spots
        gymClassService.increaseAvailableSpots(booking.getGymClass().getId());

        return booking;
    }

    // Delete booking (ADMIN only - hard delete)
    @Transactional
    public void deleteBooking(Long bookingId) {
        Booking booking = getBookingById(bookingId);

        // If booking was confirmed, restore available spots
        if (booking.getStatus() == BookingStatus.CONFIRMED) {
            gymClassService.increaseAvailableSpots(booking.getGymClass().getId());
        }

        bookingRepository.delete(booking);
    }

    // Check if user can book a class
    public boolean canUserBookClass(Long userId, Long classId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        GymClass gymClass = gymClassService.getClassById(classId);

        // Check if class is available
        if (!gymClassService.isClassAvailable(classId)) {
            return false;
        }

        // Check if user already booked this class
        return !bookingRepository.existsByUserAndGymClassAndStatus(user, gymClass, BookingStatus. CONFIRMED);
    }
}
