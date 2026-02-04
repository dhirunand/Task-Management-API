# Spring Boot REST Application

This is a sample *Spring Boot* application built using *Java* and *Maven, demonstrating REST APIs with **Spring Web MVC, **Spring Data JPA, **H2 in-memory database, **Lombok, and **Bean Validation*.

The project is designed to be easy to set up and run locally with minimal configuration.

---

## 🛠 Tech Stack & Libraries

- *Java* (17 or above recommended)
- *Spring Boot*
- *Spring Web MVC* – for building REST APIs
- *Spring Data JPA* – for database interaction
- *H2 Database* – in-memory database for development/testing
- *Lombok* – to reduce boilerplate code
- *Spring Boot Starter Validation* – for request validation
- *Maven* – build and dependency management tool

---

## 📦 Project Dependencies

The project uses the following Spring Boot starters:

```xml
<dependencies>
    <!-- Web MVC -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- H2 Database -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>