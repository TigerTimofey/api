package com.api.movie.controllers;

import com.api.movie.entities.Movie;
import com.api.movie.entities.Actor;
import com.api.movie.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    // Create a new movie
    @PostMapping
    public ResponseEntity<Movie> createMovie(@Valid @RequestBody Movie movie) {
        Movie savedMovie = movieService.createMovie(movie);
        return ResponseEntity.ok(savedMovie);
    }

    // Retrieve all movies
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    // Retrieve a specific movie by ID
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Update a movie
    @PatchMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        Movie updatedMovie = movieService.updateMovie(id, movieDetails);
        return ResponseEntity.ok(updatedMovie);
    }

    // Delete a movie
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

// Filter movies by genre
@GetMapping("/filter/genre")
public ResponseEntity<List<Movie>> findMoviesByGenre(@RequestParam("genre") Long genreId) {
    List<Movie> movies = movieService.findMoviesByGenre(genreId);
    return ResponseEntity.ok(movies);
}



    // Filter movies by release year
    @GetMapping("/filter")
    public ResponseEntity<List<Movie>> findMoviesByReleaseYear(@RequestParam("year") Integer releaseYear) {
        List<Movie> movies = movieService.findMoviesByReleaseYear(releaseYear);
        return ResponseEntity.ok(movies);
    }
    // Add actor to a movie
    @PostMapping("/{movieId}/actors/{actorId}")
    public ResponseEntity<Movie> addActorToMovie(@PathVariable Long movieId, @PathVariable Long actorId) {
        Movie updatedMovie = movieService.addActorToMovie(movieId, actorId);
        return ResponseEntity.ok(updatedMovie);
    }

    // Get all actors in a specific movie
    @GetMapping("/{movieId}/actors")
    public ResponseEntity<Set<Actor>> getActorsByMovie(@PathVariable Long movieId) {
        Set<Actor> actors = movieService.getActorsByMovie(movieId);
        return ResponseEntity.ok(actors);
    }

}
