package com.adamidis.gymapp.repository;

import com.adamidis.gymapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); // Custom query method to find a user by email

    Boolean existsByEmail(String email); // Check if a user exists by email

    Optional<User> findByUsername(String username); // Custom query method to find a user by username
}
