# Library Management System Project

## Project Description
This project presents a library management system as a REST API developed with Spring Boot. CRUD operations are performed for books, authors, categories, publishers, and book borrowing transactions.

## Project Setup

### Requirements
- Java JDK 17 or higher
- Maven
- PostgreSQL
- Git

### Setup Steps
1. Clone the project:
   ```sh
   git clone https://github.com/abresueda/library-management-system.git
   cd library-management-system
2. Install dependencies:   mvn clean install  
3. Configure the application.properties file:
   properties  spring.datasource.url=jdbc:postgresql://localhost:5432/library_db
   spring.datasource.username=postgres
   spring.datasource.password=password
   spring.jpa.hibernate.ddl-auto=update  
4. Run the application:   mvn spring-boot:run

## Architectural Layers
- **Entity:** Classes representing database tables.
- **Repository:** Classes performing database operations.
- **Service:** Classes containing business logic.
- **Controller:** Classes providing API endpoints.

## Entities and Relationships

### Book
- **id:** Unique book ID
- **name:** Book name
- **publicationYear:** Publication year
- **stock:** Quantity in the library

**Relationships:**
- A book can have one author, belong to multiple categories, have one publisher, and have multiple borrowing transactions.

### Author
- **id:** Unique author ID
- **name:** Author's name
- **birthDate:** Author's birth year
- **country:** Author's country

**Relationships:**
- An author can have multiple books.

### Category
- **id:** Unique category ID
- **name:** Category name
- **description:** Category description

**Relationships:**
- A category can have multiple books, and a book can belong to multiple categories.

### Publisher
- **id:** Unique publisher ID
- **name:** Publisher name
- **establishmentYear:** Establishment year
- **address:** Publisher address

**Relationships:**
- A publisher can have multiple books.

### Book Borrowing
- **id:** Unique borrowing ID
- **borrowerName:** Name of the borrower
- **borrowerEmail:** Email of the borrower
- **borrowingDate:** Borrowing date
- **returnDate:** Return date (null at first, updated upon return)

**Relationships:**
- A book can have multiple borrowing transactions, but each borrowing transaction belongs to only one book.

## Validator Usage
Use the necessary validator annotations to perform data validation. For example, email format check, required field check, etc.

## Notes
- Configure GET requests for publishers to exclude the address information.
- Configure the update of borrowing details to exclude email input.
- Use `@OneToMany`, `@ManyToOne`, `@ManyToMany` annotations to define relationships.
- Use Fetch and Cascade annotations where necessary.
- Use constructor injection for IoC and DI.
