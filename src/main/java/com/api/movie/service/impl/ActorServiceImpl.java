package com.api.movie.service.impl;

import com.api.movie.entities.Actor;
import com.api.movie.entities.Movie;
import com.api.movie.exceptions.ResourceNotFoundException;
import com.api.movie.repositories.ActorRepository;
import com.api.movie.repositories.MovieRepository;
import com.api.movie.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Actor createActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public Optional<Actor> getActorById(Long id) {
        return actorRepository.findById(id);
    }

    @Override
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    @Override
    public Actor updateActor(Long id, Actor actorDetails) {
        Actor actor = actorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Actor not found with id " + id));

        actor.setName(actorDetails.getName());
        actor.setBirthDate(actorDetails.getBirthDate());

        return actorRepository.save(actor);
    }

    @Override
    public void deleteActor(Long id) {
        Actor actor = actorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Actor not found with id " + id));
        actorRepository.delete(actor);
    }

    @Override
    public Actor addActorToMovie(Long actorId, Long movieId) {
        Actor actor = actorRepository.findById(actorId).orElseThrow(() ->
                new ResourceNotFoundException("Actor not found with id " + actorId));
        Movie movie = movieRepository.findById(movieId).orElseThrow(() ->
                new ResourceNotFoundException("Movie not found with id " + movieId));

        actor.getMovies().add(movie);
        actorRepository.save(actor);

        return actor;
    }

    @Override
    public Set<Movie> getMoviesByActor(Long actorId) {
        Actor actor = actorRepository.findById(actorId).orElseThrow(() ->
                new ResourceNotFoundException("Actor not found with id " + actorId));

        return actor.getMovies();
    }
}
