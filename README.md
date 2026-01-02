# 🏋️ GymBook - Gym Class Booking Application

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![Angular](https://img.shields.io/badge/Angular-17-red)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)

A full-stack web application for managing gym class bookings with role-based access control.  Built with Spring Boot REST API backend and Angular frontend.

---

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [API Documentation](#api-documentation)
- [Database Schema](#database-schema)
- [Screenshots](#screenshots)
- [Project Structure](#project-structure)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

---

## ✨ Features

### 👤 User Features
- ✅ Self-registration with secure authentication
- ✅ JWT-based authentication
- ✅ Browse available gym classes with filters
- ✅ Book classes with real-time capacity checking
- ✅ Cancel bookings
- ✅ View personal booking history
- ✅ Responsive dashboard

### 👨‍💼 Admin Features
- ✅ Complete CRUD operations for gym classes
- ✅ Manage class schedules (day, time, capacity)
- ✅ View all bookings across all classes
- ✅ Monitor class utilization
- ✅ Admin dashboard with analytics

### 🔒 Security
- JWT token-based authentication
- Role-based access control (USER/ADMIN)
- Password encryption with BCrypt
- Protected API endpoints
- CORS configuration for frontend integration

### 🎯 Business Logic
- Prevent overbooking (capacity enforcement)
- Prevent duplicate bookings by same user
- Schedule conflict validation
- Automatic booking status management

---

## 🔧 Database Configuration

Create `src/main/resources/application.properties` with the following configuration:

```properties
# Application Name
spring.application.name=gymbook-backend

# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/gymbook_db
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# JWT Configuration
jwt.secret=YOUR_SECRET_KEY_AT_LEAST_256_BITS
jwt.expiration=86400000

# Logging
logging.level.org.springframework. security=DEBUG
logging.level. com.adamidis.gymapp=DEBUG
logging.level.org.hibernate. SQL=DEBUG

# Spring Configuration
spring.main.allow-circular-references=true
```

⚠️ **Note:** `application.properties` is gitignored and must be created locally.

---
