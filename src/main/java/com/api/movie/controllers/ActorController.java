package com.api.movie.controllers;

import com.api.movie.entities.Actor;
import com.api.movie.entities.Movie;
import com.api.movie.repositories.ActorRepository;
import com.api.movie.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.Map;


import java.util.Set;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private MovieRepository movieRepository;

    // Create a new Actor POST
    @PostMapping
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
        Actor savedActor = actorRepository.save(actor);
        return ResponseEntity.ok(savedActor);
    }

    // Update a new Actor using PATCH
    @PatchMapping("/{actorId}")
    public ResponseEntity<Actor> updateActor(@PathVariable Long actorId, @RequestBody Map<String, Object> updates) {
        Actor actor = actorRepository.findById(actorId).orElseThrow();

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Actor.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, actor, value);
            }
        });

        Actor updatedActor = actorRepository.save(actor);
        return ResponseEntity.ok(updatedActor);
    }
    // Get all Actors
    @GetMapping
    public ResponseEntity<Set<Actor>> getAllActors() {
        return ResponseEntity.ok(Set.copyOf(actorRepository.findAll()));
    }

    // Associate Actor with Movie
    @PostMapping("/{actorId}/movies/{movieId}")
    public ResponseEntity<Actor> addActorToMovie(@PathVariable Long actorId, @PathVariable Long movieId) {
        Actor actor = actorRepository.findById(actorId).orElseThrow();
        Movie movie = movieRepository.findById(movieId).orElseThrow();

        actor.getMovies().add(movie);
        movie.getActors().add(actor);

        actorRepository.save(actor);
        movieRepository.save(movie);

        return ResponseEntity.ok(actor);
    }


    // Get all Movies by Actor
    @GetMapping("/{actorId}/movies")
    public ResponseEntity<Set<Movie>> getMoviesByActor(@PathVariable Long actorId) {
        Actor actor = actorRepository.findById(actorId).orElseThrow();
        return ResponseEntity.ok(actor.getMovies());
    }
}
