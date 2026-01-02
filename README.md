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
