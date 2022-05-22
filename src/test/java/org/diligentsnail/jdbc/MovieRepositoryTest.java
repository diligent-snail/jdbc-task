package org.diligentsnail.jdbc;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MovieRepositoryTest {
	private final MovieRepository movieRepository = new JdbcMovieRepository("jdbc:derby:memory:movies;create=true");

	MovieRepositoryTest() throws SQLException {
	}

	@Order(0)
	@Test
	void noMovies() throws SQLException {
		assertEquals(0, movieRepository.count());
	}

	@Order(1)
	@Test
	void fightClubInserted() throws SQLException {
		Movie movie = new Movie("Fight Club");
		movieRepository.saveOne(movie);

		assertNotNull(movie.getId());
		assertEquals(1, movieRepository.count());
	}

	@Order(2)
	@Test
	void fightClubInserted2AndFound() throws SQLException {
		Movie movie = new Movie("Fight Club");
		movieRepository.saveOne(movie);
		Optional<Movie> optional = movieRepository.findById(movie.getId());
		assertTrue(optional.isPresent());
		assertEquals("Fight Club", optional.get().getTitle());
	}

	@Order(3)
	@Test
	void allFound() throws SQLException {
		List<Movie> movies = movieRepository.findAll();
		assertEquals(2, movies.size());
		assertEquals("Fight Club", movies.get(0).getTitle());
	}
}
