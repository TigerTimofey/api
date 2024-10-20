package com.api.movie.controllers;

import com.api.movie.entities.Actor;
import com.api.movie.entities.Movie;
import com.api.movie.exceptions.ResourceNotFoundException;
import com.api.movie.repositories.ActorRepository;
import com.api.movie.repositories.MovieRepository;
import com.api.movie.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private ActorService actorService;
    @Autowired
    private MovieRepository movieRepository;

    // Create a new Actor POST
    @PostMapping
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
        Actor savedActor = actorService.createActor(actor);
        return ResponseEntity.ok(savedActor);
    }


    // Update a new Actor using PATCH
    @PatchMapping("/{actorId}")
    public ResponseEntity<Actor> updateActor(@PathVariable Long actorId, @RequestBody Map<String, Object> updates) {
        Actor actor = actorService.getActorById(actorId).orElseThrow();

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Actor.class, key);
            if (field != null) {
                field.setAccessible(true);

                // Handle LocalDate field conversion
                if ("birthDate".equals(key) && value instanceof String) {
                    LocalDate birthDate = LocalDate.parse((String) value); // Converts String to LocalDate
                    ReflectionUtils.setField(field, actor, birthDate);
                } else {
                    ReflectionUtils.setField(field, actor, value);
                }
            }
        });

        Actor updatedActor = actorService.updateActor(actorId, actor);
        return ResponseEntity.ok(updatedActor);
    }

    @GetMapping
    public ResponseEntity<List<Actor>> getAllActors() {
        return ResponseEntity.ok(actorService.getAllActors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable Long id) {
        Optional<Actor> actor = actorService.getActorById(id);
        return actor.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id " + id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteActor(@PathVariable Long id) {
        try {
            actorService.deleteActor(id);  // Call the delete method
            return ResponseEntity.noContent().build();  // 204 No Content if successful
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());  // 400 Bad Request if the deletion is prevented
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Actor>> findActorsByName(@RequestParam String name) {
        List<Actor> actors = actorService.findActorsByName(name);
        return ResponseEntity.ok(actors);
    }


//    @GetMapping("/{actorId}/movies")
//    public ResponseEntity<Set<Movie>> getMoviesByActor(@PathVariable Long actorId) {
//        Set<Movie> movies = actorService.getMoviesByActor(actorId); // Uses the service layer
//        return ResponseEntity.ok(movies);
//    }



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