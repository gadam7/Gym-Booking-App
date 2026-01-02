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

## 🛠️ Tech Stack

### Backend
- **Framework:** Spring Boot 3.x
- **Security:** Spring Security + JWT
- **Database:** MySQL 9.5
- **ORM:** Spring Data JPA / Hibernate
- **Build Tool:** Maven
- **Java Version:** 17

### Frontend
- **Framework:** Angular 17
- **Language:** TypeScript
- **UI Library:** Bootstrap 5 / Angular Material
- **HTTP Client:** Angular HttpClient
- **State Management:** RxJS

### DevOps & Tools
- Git & GitHub
- Postman (API Testing)
- MySQL Workbench
- VS Code / IntelliJ IDEA

---

## 🏗️ Architecture

```
┌─────────────────┐         HTTP/JSON          ┌──────────────────┐
│                 │  ◄──────────────────────►  │                  │
│  Angular        │      REST API Calls        │   Spring Boot    │
│  Frontend       │       (JWT Auth)           │   Backend        │
│                 │                            │                  │
└─────────────────┘                            └────────┬─────────┘
                                                        │
                                                        │ JPA/Hibernate
                                                        │
                                                        ▼
                                                ┌─────────────────┐
                                                │                 │
                                                │  MySQL Database │
                                                │                 │
                                                └─────────────────┘
```

**Design Pattern:** MVC (Model-View-Controller) with Service Layer  
**API Style:** RESTful  
**Authentication:** Stateless JWT  
**ORM:** JPA with Hibernate (Auto DDL)

---

## 📦 Prerequisites

Before you begin, ensure you have the following installed:

- **Java JDK 17** or higher - [Download](https://www.oracle.com/java/technologies/downloads/)
- **Node.js 18+** and npm - [Download](https://nodejs.org/)
- **MySQL 9.5+** - [Download](https://dev.mysql.com/downloads/)
- **Maven 3.8+** - [Download](https://maven.apache.org/download.cgi)
- **Angular CLI** - Install via:  `npm install -g @angular/cli`
- **Git** - [Download](https://git-scm.com/)

---

## 🚀 Installation & Setup

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/gadam7/gymbook.git
cd gymbook
```

### 2️⃣ Backend Setup

#### Step 1: Create MySQL Database

**Important:** You only need to create the database.  JPA/Hibernate will automatically create all tables!

```sql
# Login to MySQL
mysql -u root -p

# Create database only
CREATE DATABASE gymbook_db;

# Optional: Create dedicated user
CREATE USER 'gymbook_user'@'localhost' IDENTIFIED BY 'yourpassword';
GRANT ALL PRIVILEGES ON gymbook_db.* TO 'gymbook_user'@'localhost';
FLUSH PRIVILEGES;
EXIT;
```

#### Step 2: Configure Application Properties

Navigate to `backend/src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/gymbook_db
spring.datasource.username=gymbook_user
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate. format_sql=true

# JWT Configuration
jwt.secret=YourSecretKeyHere_MakeItLongAndSecure_AtLeast256BitsForHS512Algorithm
jwt.expiration=86400000

# Server Configuration
server.port=8080

# Logging
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

#### Step 3: Build and Run Backend

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

✅ Backend will start at: `http://localhost:8080`  
✅ Database tables will be **automatically created** by Hibernate!

**Check your database:**
```sql
USE gymbook_db;
SHOW TABLES;
# You should see: users, gym_classes, bookings
```