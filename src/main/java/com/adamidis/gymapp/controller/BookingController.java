package com.adamidis.gymapp.controller;

import com.adamidis.gymapp.dto.BookingRequest;
import com.adamidis.gymapp.dto.BookingResponse;
import com.adamidis.gymapp.dto.MessageResponse;
import com.adamidis.gymapp.model.Booking;
import com.adamidis.gymapp.security.UserPrincipal;
import com.adamidis.gymapp.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Get all bookings (ADMIN ONLY)
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        List<BookingResponse> response = bookings.stream()
                .map(BookingResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // Get current user's bookings
    @GetMapping("/my-bookings")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<BookingResponse>> getMyBookings(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<Booking> bookings = bookingService.getUserBookings(userPrincipal. getId());
        List<BookingResponse> response = bookings.stream()
                .map(BookingResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // Get current user's active bookings (CONFIRMED only)
    @GetMapping("/my-active-bookings")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<BookingResponse>> getMyActiveBookings(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<Booking> bookings = bookingService.getUserActiveBookings(userPrincipal. getId());
        List<BookingResponse> response = bookings.stream()
                .map(BookingResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // Get bookings for a specific class (ADMIN ONLY)
    @GetMapping("/class/{classId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BookingResponse>> getClassBookings(@PathVariable Long classId) {
        try {
            List<Booking> bookings = bookingService.getClassBookings(classId);
            List<BookingResponse> response = bookings.stream()
                    .map(BookingResponse:: new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Create booking
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<? > createBooking(@Valid @RequestBody BookingRequest request,
                                            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        try {
            Booking booking = bookingService.createBooking(userPrincipal.getId(), request.getClassId());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new BookingResponse(booking));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse(e.getMessage()));
        }
    }

    // Cancel booking
    @PutMapping("/{id}/cancel")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> cancelBooking(@PathVariable Long id,
                                           @AuthenticationPrincipal UserPrincipal userPrincipal) {
        try {
            Booking booking = bookingService.cancelBooking(id, userPrincipal. getId());
            return ResponseEntity. ok(new BookingResponse(booking));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse(e.getMessage()));
        }
    }

    // Delete booking (ADMIN ONLY - hard delete)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        try {
            bookingService.deleteBooking(id);
            return ResponseEntity.ok(new MessageResponse("Booking deleted successfully! "));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse(e.getMessage()));
        }
    }

    // Check if user can book a class
    @GetMapping("/can-book/{classId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> canBookClass(@PathVariable Long classId,
                                          @AuthenticationPrincipal UserPrincipal userPrincipal) {
        try {
            boolean canBook = bookingService.canUserBookClass(userPrincipal.getId(), classId);
            return ResponseEntity.ok(new MessageResponse(canBook ?  "Can book" : "Cannot book"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse(e.getMessage()));
        }
    }
}
