package com.api.movie.service;

import com.api.movie.entities.Actor;
import com.api.movie.entities.Movie;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ActorService {

    //create new actor
    Actor createActor(Actor actor);

    //retrieve specific actor by id
    Optional<Actor> getActorById(Long id);

    //retrieve list of actors
    List<Actor> getAllActors();

    //modify actor details
    Actor updateActor(Long id, Actor actorDetails);

    //remove actor
    void deleteActor(Long id);

    //filter actors by name
    List<Actor> findActorsByName(String name);

    //fetch all movies an actor has appeared in
    Set<Movie> getMoviesByActor(Long actorId);





    Actor addActorToMovie(Long actorId, Long movieId);

}
