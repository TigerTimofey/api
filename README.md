# Movies API - Spring Boot and JPA

## Table of Contents 📑
- [Project Overview 🎬](#project-overview-)
- [Features ⚙️](#features-)
- [Setup and Installation 🚀](#setup-and-installation-)
- [API Endpoints 📖](#api-endpoints-)
    - [Genre Endpoints](#genre-endpoints)
    - [Movie Endpoints](#movie-endpoints)
    - [Actor Endpoints](#actor-endpoints)
- [Sample Data](#sample-data)
- [Additional Features ✨](#additional-features-)
- [Testing the API 🧪](#testing-the-api-)
- [Challenges and Solutions 💡](#challenges-and-solutions-)

## Project Overview 🎬

This project is a **RESTful API** for managing a movie database using **Spring Boot** and **JPA**. It allows performing **CRUD** operations on `movies`, `genres`, and `actors`, and managing the relationships between them.

## Features ⚙️
- **CRUD Operations** for `movies`, `genres`, and `actors`.
- **Many-to-Many Relationships**:
    - A `movie` can belong to multiple `genres`.
    - A `movie` can have multiple `actors`.
- **Filtering** movies by `genre`, `release year`, or `actor`.
- **Pagination** support for large datasets.
- **Error Handling** with validation and custom exceptions.
- **SQLite** database for persistence.
- **PATCH** method for partial updates.

## Setup and Installation 🚀

### Prerequisites
Make sure you have the following installed:
- **Java 17+**
- **Maven**
- **SQLite**

### Steps to Run:

1. **Clone the Repository**:
    ```bash
    git clone https://gitea.kood.tech/tiger/kmdb
    cd kmdb
    ```

2. **Configure Database**:
   Add the following to `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:sqlite:movies_database.db
    spring.datasource.driver-class-name=org.sqlite.JDBC
    spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    ```

3. **Install Dependencies**:
    ```bash
    mvn clean install
    ```

4. **Run the Application**:
    ```bash
    mvn spring-boot:run
    ```

## API Endpoints 📖

## Genre Endpoints

- **Create a new genre**:
  ```http
  POST /api/genres
  ```
- **Retrieve all genres**:
  ```http
  GET /api/genres
  ```
- **Retrieve a specific genre**:
  ```http
  GET /api/genres/{id}
  ```
- **Update a genre**:
  ```http
  PATCH /api/genres/{id}
  ```
- **Delete a genre**:
  ```http
  DELETE /api/genres/{id}
  ```

## Movie Endpoints

- **Create a new movie**:
  ```http
  POST /api/movies
  ```
- **Retrieve all movies**:
  ```http
  GET /api/movies
  ```
- **Retrieve a specific movie**:
  ```http
  GET /api/movies/{id}
  ```
- **Retrieve movies by genre**:
  ```http
  GET /api/movies?genre={genreId}
  ```
- **Retrieve movies by year**:
  ```http
  GET /api/movies?year={releaseYear}
  ```
- **Retrieve movies by actor**:
  ```http
  GET /api/movies?actor={actorId}
  ```
- **Update a movie**:
  ```http
  PATCH /api/movies/{id}
  ```
- **Delete a movie**:
  ```http
  DELETE /api/movies/{id}
  ```

## Actor Endpoints

- **Create a new actor**:
  ```http
  POST /api/actors
  ```
- **Retrieve all actors**:
  ```http
  GET /api/actors
  ```
- **Retrieve a specific actor**:
  ```http
  GET /api/actors/{id}
  ```
- **Retrieve actors by name**:
  ```http
  GET /api/actors?name={name}
  ```
- **Update an actor**:
  ```http
  PATCH /api/actors/{id}
  ```
- **Delete an actor**:
  ```http
  DELETE /api/actors/{id}
  ```

## Sample Data

- **Genres**: Action, Sci-Fi, Drama, Comedy, Thriller
- **Movies**: A collection of 20 movies spanning 20 years, some with multiple genres.
- **Actors**: At least 15 actors, some starring in multiple movies.

## Additional Features ✨

- **Force Deletion**: Option to force delete entities that have existing relationships by using a force parameter in the DELETE endpoint.
- **Search by Title**: Search for movies by title using a partial, case-insensitive match:
  ```http
  GET /api/movies/search?title=matrix
  ```

## Testing the API 🧪

To test the API, you can use Postman:

1. Import the provided collection (Movie Database API) into Postman.
2. Test each endpoint using sample JSON data.
3. Verify response statuses and payloads.

## Challenges and Solutions 💡

- **Managing Many-to-Many Relationships**: JPA annotations were used to handle the many-to-many relationships between movies, genres, and actors.
- **Input Validation**: Bean Validation annotations ensured that required fields were validated in requests.
- **Error Handling**: Custom exceptions and global exception handling improved user experience by providing meaningful error messages.

This project is a comprehensive exercise in building a RESTful API with Spring Boot, JPA, and SQLite, designed to demonstrate your ability to manage complex relationships and develop robust, scalable APIs.
