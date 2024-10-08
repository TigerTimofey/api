package com.api.movie;

import com.api.movie.entities.Actor;
import com.api.movie.entities.Movie;
import com.api.movie.entities.Genre;
import com.api.movie.repositories.ActorRepository;
import com.api.movie.repositories.GenreRepository;
import com.api.movie.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieApplicationTests {

	@Autowired
	private ActorRepository actorRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private GenreRepository genreRepository;

	@Test
	@Transactional
	public void testCreateAndFetchActorAndMovie() {
		// Create and save a genre
		Genre genre = new Genre("Action");
		genreRepository.save(genre);

		// Create and save an actor
//		Actor actor = new Actor("Leonardo DiCaprio", Instant.parse("1974-11-11T00:00:00Z"));
		Actor actor = new Actor("Leonardo DiCaprio", LocalDate.parse("1965-04-04"));
		actorRepository.save(actor);

		// Create and save a movie
		Movie movie = new Movie("Inception", 2010, 148);
		movie.getGenres().add(genre);
		movie.getActors().add(actor);
		movieRepository.save(movie);

		// Fetch the movie and check relationships
		Movie fetchedMovie = movieRepository.findById(movie.getId()).orElse(null);
		assertThat(fetchedMovie).isNotNull();
		assertThat(fetchedMovie.getActors()).contains(actor);
		assertThat(fetchedMovie.getGenres()).contains(genre);
	}
}
