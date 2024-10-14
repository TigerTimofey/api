package com.api.movie.repositories;

import com.api.movie.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByGenresNameContainingIgnoreCase(String genreName);
    List<Movie> findByReleaseYear(Integer releaseYear);
}
