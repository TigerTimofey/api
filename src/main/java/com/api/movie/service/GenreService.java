package com.api.movie.service;

import com.api.movie.entities.Genre;
import com.api.movie.entities.Movie;

import java.util.List;

public interface GenreService {

    // Create a new genre
    Genre createGenre(Genre genre);

    // Retrieve all genres
    List<Genre> getAllGenres();

    // Retrieve a specific genre by id
    Genre getGenreById(Long id);

    // Modify a genre's name
    Genre updateGenre(Long id, Genre newName);

    // Delete a genre by id
    void deleteGenre(Long id);

    // Get all genres related to a specific movie
    List<Genre> findGenresByMovieId(Long movieId);

    // Add a genre to a movie
    Movie addGenreToMovie(Long movieId, Long genreId);
}
