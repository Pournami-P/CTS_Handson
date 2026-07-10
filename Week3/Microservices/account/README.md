# Account Microservice

This is a Spring Boot project implementing the Account Microservice as part of the Digital Nurture 5.0 Java FSE program.

## Features
- Manages user accounts using an in-memory H2 database.
- Exposes REST endpoints to query accounts by account number.
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

### Get Account by Number
- **URL:** `/accounts/{number}`
- **Method:** `GET`
- **Path Variable:** `number` (e.g. `00987987973432`)
- **Success Response (200 OK):**
  ```json
  {
    "number": "00987987973432",
    "type": "savings",
    "balance": 234343.0
  }
  ```
- **Error Response (404 Not Found):**
  ```json
  {
    "timestamp": "2026-07-02T22:30:15.123",
    "message": "Account not found with number: 12345",
    "details": "uri=/accounts/12345"
  }
  ```

## H2 Console
The in-memory database console can be accessed at:
- **URL:** `http://localhost:8080/h2-console`
- **JDBC URL:** `jdbc:h2:mem:accountdb`
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
