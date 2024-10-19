package com.api.movie.service;

import com.api.movie.entities.Movie;
import com.api.movie.entities.Actor;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MovieService {

    // Create a new movie
    Movie createMovie(Movie movie);

    // Retrieve all movies
    List<Movie> getAllMovies();

    // Retrieve a specific movie by ID
    Optional<Movie> getMovieById(Long id);

    // Update movie details
    Movie updateMovie(Long id, Movie movieDetails);

    // Remove a movie by ID
    void deleteMovie(Long id);

    // Filter movies by genre
    List<Movie> findMoviesByGenre(Long genreId);

    // Filter movies by release year
    List<Movie> findMoviesByReleaseYear(Integer releaseYear);

    // Add actor to Movie
    Movie addActorToMovie(Long movieId, Long actorId);

    // Get all actors in a specific movie
    Set<Actor> getActorsByMovie(Long movieId);

    //Extra
    //Filter by movie name
    List<Movie> findMoviesByName(String movieTitle);

}
