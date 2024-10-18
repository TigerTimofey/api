package com.api.movie.controllers;

import com.api.movie.entities.Genre;
import com.api.movie.entities.Movie;
import com.api.movie.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    // Create a new genre
    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        Genre savedGenre = genreService.createGenre(genre);
        return ResponseEntity.ok(savedGenre);
    }

    // Retrieve all genres
    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    // Retrieve a genre by ID
    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
        Genre genre = genreService.getGenreById(id);
        return ResponseEntity.ok(genre);
    }

    // Update a genre's name
    @PatchMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre newName) {
        Genre updatedGenre = genreService.updateGenre(id, newName);
        return ResponseEntity.ok(updatedGenre);
    }

    // Delete a genre
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGenre(@PathVariable Long id) {
        try {
            genreService.deleteGenre(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Get all genres for a specific movie
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Genre>> getGenresByMovie(@PathVariable Long movieId) {
        List<Genre> genres = genreService.findGenresByMovieId(movieId);
        return ResponseEntity.ok(genres);
    }

    @PostMapping("/{genreId}/movies/{movieId}")
    public ResponseEntity<Movie> addGenreToMovie(@PathVariable Long genreId, @PathVariable Long movieId) {
        Movie updatedMovie = genreService.addGenreToMovie(movieId, genreId);
        return ResponseEntity.ok(updatedMovie);
    }
}
