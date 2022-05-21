package org.diligentsnail.jdbc;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMovieRepository implements MovieRepository {

	private final String url;

	public JdbcMovieRepository(String url) throws SQLException {
		this.url = url;

		try (Connection connection = DriverManager.getConnection(url);
			 Statement statement = connection.createStatement()) {
			statement.execute("""
					CREATE TABLE MOVIES(
						ID BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
						TITLE VARCHAR(255) NOT NULL 
					)
					""");
		}
	}

	@Override
	public Optional<Movie> findById(long id) throws SQLException {
		try (Connection connection = DriverManager.getConnection(url);
			 PreparedStatement statement = connection.prepareStatement(
					 "SELECT ID, TITLE FROM MOVIES WHERE ID = ?")) {
			statement.setLong(1, id);
			try (ResultSet result = statement.executeQuery()) {
				if (result.next()) {
					Movie movie = new Movie();
					movie.setId(result.getLong("ID"));
					movie.setTitle(result.getString("TITLE"));
					return Optional.of(movie);
				} else {
					return Optional.empty();
				}
			}

		}
	}

	@Override
	public List<Movie> findAll() throws SQLException {
		try (Connection connection = DriverManager.getConnection(url);
			 Statement statement = connection.createStatement();
			 ResultSet result = statement.executeQuery("SELECT ID, TITLE FROM MOVIES")) {
			List<Movie> allMovies = new ArrayList<>();
			while (result.next()) {
				Movie movie = new Movie();
				movie.setId(result.getLong("ID"));
				movie.setTitle(result.getString("TITLE"));
				allMovies.add(movie);
			}

			return allMovies;
		}
	}

	@Override
	public void saveOne(Movie movie) throws SQLException {
		if (movie.getId() != null) {
			throw new IllegalStateException("Not implemented");
		}

		try (Connection connection = DriverManager.getConnection(url);
			 PreparedStatement statement = connection.prepareStatement(
					 "INSERT INTO MOVIES (TITLE) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {

			statement.setString(1, movie.getTitle());
			statement.executeUpdate();

			try (var keys = statement.getGeneratedKeys()) {
				keys.next();
				var id = keys.getLong(1);
				movie.setId(id);
			}
		}
	}

	@Override
	public long count() throws SQLException {
		try (Connection connection = DriverManager.getConnection(url);
			 Statement statement = connection.createStatement();
			 ResultSet result = statement.executeQuery("SELECT COUNT(*) FROM MOVIES")) {
			result.next();
			return result.getLong(1);
		}
	}

	@Override
	public List<Movie> findByTitleContaining(String substring) throws SQLException {
		return null;
	}

	@Override
	public void deleteById(long id) throws SQLException {

	}
}
