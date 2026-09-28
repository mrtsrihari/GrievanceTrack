# GrievanceTrack

**GrievanceTrack** is a Spring Boot application designed for citizen complaint tracking with built-in SLA (Service Level Agreement) escalation mechanisms. It allows citizens to submit grievances, departments to manage them, and automatically handles escalations if complaints are not resolved within their SLA.

## Features

*   **Citizen Management**: Endpoints for managing citizen profiles and details.
*   **Department Management**: Handling different government or organizational departments.
*   **Grievance Tracking**: Core system to log, track, and resolve citizen grievances.
*   **SLA Escalation**: Automatic escalations when a grievance breaches its assigned timeframe.

## Technologies Used

*   **Java 21**
*   **Spring Boot 3.3.4**
    *   Spring Web
    *   Spring Data JPA
    *   Spring Boot Validation
*   **MySQL** (Database)
*   **Maven** (Build Tool)

## Prerequisites

*   Java Development Kit (JDK) 21 or higher
*   Maven 3.8+
*   MySQL Server

## Getting Started

### 1. Database Configuration

Create a MySQL database for the application.
Update your database configuration in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```

### 2. Running the Application

You can run the application directly using the Maven wrapper or your local Maven installation:

```bash
mvn spring-boot:run
```

### 3. Accessing the Application

Once started, the application will be running at:
`http://localhost:8080`

You can access the various REST endpoints for `/citizens`, `/departments`, and `/grievances` from there.

## Project Structure

*   `controller/`: Contains REST controllers (`GrievanceController`, `DepartmentController`, `CitizenController`).
*   `dto/`: Data Transfer Objects (e.g., `GrievanceRequest`).
*   `repository/`: Spring Data JPA Repositories (e.g., `EscalationRepository`).
*   `service/`: Business logic layer.
*   `model/`: JPA entities representing database tables.
