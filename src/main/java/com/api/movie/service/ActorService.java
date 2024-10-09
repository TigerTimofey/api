package com.api.movie.service;

import com.api.movie.entities.Actor;
import com.api.movie.entities.Movie;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ActorService {
    Actor createActor(Actor actor);
    Optional<Actor> getActorById(Long id);
    List<Actor> getAllActors();
    Actor updateActor(Long id, Actor actorDetails);
    void deleteActor(Long id);
    Actor addActorToMovie(Long actorId, Long movieId);
    Set<Movie> getMoviesByActor(Long actorId);
}
