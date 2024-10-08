package com.api.movie.controllers;

import com.api.movie.entities.Genre;
import com.api.movie.entities.Movie;
import com.api.movie.repositories.GenreRepository;
import com.api.movie.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieRepository movieRepository;

    // Create a new Genre
    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        Genre savedGenre = genreRepository.save(genre);
        return ResponseEntity.ok(savedGenre);
    }

    // Get all Genres
    @GetMapping
    public ResponseEntity<Set<Genre>> getAllGenres() {
        return ResponseEntity.ok(Set.copyOf(genreRepository.findAll()));
    }

    // Associate Genre with Movie
    @PostMapping("/{genreId}/movies/{movieId}")
    public ResponseEntity<Genre> addGenreToMovie(@PathVariable Long genreId, @PathVariable Long movieId) {
        Genre genre = genreRepository.findById(genreId).orElseThrow();
        Movie movie = movieRepository.findById(movieId).orElseThrow();

        movie.getGenres().add(genre);
        genre.getMovies().add(movie);

        movieRepository.save(movie);
        genreRepository.save(genre);

        return ResponseEntity.ok(genre);
    }

    // Get all Movies by Genre
    @GetMapping("/{genreId}/movies")
    public ResponseEntity<Set<Movie>> getMoviesByGenre(@PathVariable Long genreId) {
        Genre genre = genreRepository.findById(genreId).orElseThrow();
        return ResponseEntity.ok(genre.getMovies());
    }
}
