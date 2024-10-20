package com.api.movie.repositories;

import com.api.movie.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByGenresId(Long genreId);
    List<Movie> findByReleaseYear(Integer releaseYear);
    List<Movie> findByTitleContainingIgnoreCase(String movieTitle);

}
