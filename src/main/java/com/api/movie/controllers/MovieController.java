package com.api.movie.controllers;

import com.api.movie.entities.Actor;
import com.api.movie.entities.Movie;
import com.api.movie.entities.Genre;
import com.api.movie.repositories.ActorRepository;
import com.api.movie.repositories.GenreRepository;
import com.api.movie.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private GenreRepository genreRepository;

    // Create an Actor
    @PostMapping("/actors")
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
        Actor savedActor = actorRepository.save(actor);
        return ResponseEntity.ok(savedActor);
    }

    // Create a Movie
    @PostMapping("/movies")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        return ResponseEntity.ok(savedMovie);
    }

    // Create a Genre
    @PostMapping("/genres")
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        Genre savedGenre = genreRepository.save(genre);
        return ResponseEntity.ok(savedGenre);
    }

    // Associate Actor with Movie
    @PostMapping("/movies/{movieId}/actors/{actorId}")
    public ResponseEntity<Movie> addActorToMovie(@PathVariable Long movieId, @PathVariable Long actorId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        Actor actor = actorRepository.findById(actorId).orElseThrow();
        movie.getActors().add(actor);
        actor.getMovies().add(movie);
        movieRepository.save(movie);
        actorRepository.save(actor);
        return ResponseEntity.ok(movie);
    }
}
