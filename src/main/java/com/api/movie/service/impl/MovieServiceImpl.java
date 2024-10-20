package com.api.movie.service.impl;

import com.api.movie.entities.Actor;
import com.api.movie.entities.Movie;
import com.api.movie.entities.Genre;
import com.api.movie.exceptions.ResourceNotFoundException;
import com.api.movie.repositories.MovieRepository;
import com.api.movie.repositories.GenreRepository;
import com.api.movie.repositories.ActorRepository;
import com.api.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.*;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public Movie updateMovie(Long id, Movie movieDetails) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id " + id));

        movie.setTitle(movieDetails.getTitle());
        movie.setReleaseYear(movieDetails.getReleaseYear());
        movie.setDuration(movieDetails.getDuration());
        movie.setGenres(movieDetails.getGenres());
        movie.setActors(movieDetails.getActors());

        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Long id) {
        // Find the movie by ID
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id " + id));

        // Check if the movie has associated genres
        if (!movie.getGenres().isEmpty()) {
            // Throw an exception if there are associated genres
            throw new IllegalStateException("Cannot delete movie '" + movie.getTitle() + "' because it is associated with genres.");
        }

        // Check if the movie has associated actors
        if (!movie.getActors().isEmpty()) {
            // Throw an exception if there are associated actors
            throw new IllegalStateException("Cannot delete movie '" + movie.getTitle() + "' because it is associated with actors.");
        }

        // If no associations are present, delete the movie
        movieRepository.delete(movie);
    }


    @Override
    public List<Movie> findMoviesByGenre(Long genreId) {
        return movieRepository.findByGenresId(genreId);
    }

    @Override
    public List<Movie> findMoviesByReleaseYear(Integer releaseYear) {
        return movieRepository.findByReleaseYear(releaseYear);
    }

    @Override
    public Movie addActorToMovie(Long movieId, Long actorId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id " + movieId));

        Actor actor = actorRepository.findById(actorId)
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id " + actorId));

        // Add the actor to the movie's actor set
        movie.getActors().add(actor);
        actor.getMovies().add(movie);

        // Save both entities to update the relationship
        movieRepository.save(movie);
        actorRepository.save(actor);

        return movie;
    }


    @Override
    public Set<Actor> getActorsByMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id " + movieId));
        return movie.getActors();
    }

    //Extra
    //Movie by name
    @Override
    public List<Movie> findMoviesByName(String movieTitle) {
        return movieRepository.findByTitleContainingIgnoreCase(movieTitle);
    }



}
