# Loan Microservice

This is a Spring Boot project implementing the Loan Microservice as part of the Digital Nurture 5.0 Java FSE program.

## Features
- Manages user loans using an in-memory H2 database.
- Exposes REST endpoints to query loans by loan account number.
- Exposes H2 console for development inspection.

## Prerequisites
- Java 17 or higher
- Maven 3.6+

## Technologies
- Spring Boot 3.3.1
- Spring Data JPA
- H2 Database (In-Memory)
- Lombok

## API Endpoints

### Get Loan by Number
- **URL:** `/loans/{number}`
- **Method:** `GET`
- **Path Variable:** `number` (e.g. `H00987987972342`)
- **Success Response (200 OK):**
  ```json
  {
    "number": "H00987987972342",
    "type": "car",
    "loan": 400000.0,
    "emi": 3258.0,
    "tenure": 18
  }
  ```
- **Error Response (404 Not Found):**
  ```json
  {
    "timestamp": "2026-07-02T22:30:15.123",
    "message": "Loan not found with number: 12345",
    "details": "uri=/loans/12345"
  }
  ```

## H2 Console
The in-memory database console can be accessed at:
- **URL:** `http://localhost:8081/h2-console`
- **JDBC URL:** `jdbc:h2:mem:loandb`
- **Username:** `sa`
- **Password:** `sa`

## Build and Run

### Package
```bash
mvn clean package
```

### Run
```bash
mvn spring-boot:run
```
