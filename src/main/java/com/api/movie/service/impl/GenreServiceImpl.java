package com.api.movie.service.impl;

import com.api.movie.entities.Genre;
import com.api.movie.entities.Movie;
import com.api.movie.exceptions.ResourceNotFoundException;
import com.api.movie.repositories.GenreRepository;
import com.api.movie.repositories.MovieRepository;
import com.api.movie.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieRepository movieRepository;


    @Override
    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id " + id));
    }

    @Override
    public Genre updateGenre(Long id, Genre newName) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id " + id));

        genre.setName(newName.getName());
        return genreRepository.save(genre);
    }

    @Override
    public void deleteGenre(Long id) {
        // Find the Genre by ID
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id " + id));

        // Check if there are any associated movies
        if (!genre.getMovies().isEmpty()) {
            throw new IllegalStateException("Cannot delete genre " + genre.getName() + " because it has associated movies.");
        }

        // If no movies are associated, delete the genre
        genreRepository.delete(genre);
    }

    @Override
    public List<Genre> findGenresByMovieId(Long movieId) {
        return genreRepository.findByMoviesId(movieId);
    }

    @Override
    public Movie addGenreToMovie(Long movieId, Long genreId) {
        // Retrieve the movie and genre by their respective IDs
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id " + movieId));

        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id " + genreId));

        // Add the genre to the movie's genres set
        movie.getGenres().add(genre);

        // Add the movie to the genre's movies set (Optional: If bi-directional relationship is needed)
        genre.getMovies().add(movie);

        // Save both entities to persist the changes
        movieRepository.save(movie);
        genreRepository.save(genre);

        return movie;
    }
}
