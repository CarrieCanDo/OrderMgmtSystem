Here’s a concise and effective `README.md` for your project based on the provided instructions:

---

# Order Management System

## Project Overview

This project is an Order Management System built using **Spring Boot**. It manages orders and products, allowing for flexible handling of different products across various industries. The system provides basic CRUD functionalities for both orders and products.

## Methodology

The project is developed following **Test-Driven Development (TDD)** principles. Each functionality in the service layer was tested using **JUnit 5** and **Mockito** for mocking repository interactions. This ensures that every feature is tested as it is developed, maintaining a high level of quality and code coverage.

## Features

- CRUD operations for Orders and Products
- Order status management using Enum (`OrderStatus`)
- Relationships between Orders and Products (Many-to-Many)
- Comprehensive unit tests for all service layer methods

## Test Cases

The following test cases were written using **JUnit 5**:

1. **OrderServiceTest**
    - Create Order
    - Get Order by ID
    - Update Order
    - Delete Order

2. **ProductServiceTest**
    - Create Product
    - Get Product by ID
    - Update Product
    - Delete Product

## Test Results

All tests passed successfully with 100% success rate. The tests ensure that the system behaves as expected, handling both valid inputs and edge cases effectively.

## Build and Run Instructions

### Prerequisites

- **Java JDK 22**
- **Maven 3.8+**
- **IntelliJ IDEA** (or any other IDE)

### Build the Project

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd OrderMgmtSystem
   ```

2. Build the project using Maven:
   ```bash
   mvn clean install
   ```

### Running the Application

To run the application:

1. Execute the following Maven command:
   ```bash
   mvn spring-boot:run
   ```

2. The application will be available at `http://localhost:8080`.

### Running the Tests

To run all the tests, use the following command:
```bash
mvn test
```

### Accessing the H2 Database

The application uses an in-memory **H2 database**. You can access the H2 console at `http://localhost:8080/h2-console` with the following credentials:

- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** (leave blank)

---

This documentation should be concise yet informative, allowing users to understand the project, its development approach, and how to use it. Let me know if you'd like to add any more details!