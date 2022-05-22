package org.diligentsnail.jdbc;

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
		createTable();
	}

	private void createTable() throws SQLException {
		try (Connection connection = DriverManager.getConnection(url);
			 Statement statement = connection.createStatement()) {
			statement.executeUpdate("""
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
			 PreparedStatement preparedStatement = connection.prepareStatement(
					 "SELECT * FROM MOVIES WHERE ID = ?");) {
			preparedStatement.setLong(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					Movie movie = new Movie();
					movie.setTitle(resultSet.getString("TITLE"));
					movie.setId(resultSet.getLong("ID"));
					return Optional.of(movie);
				}
				return Optional.empty();
			}
		}
	}

	@Override
	public List<Movie> findAll() throws SQLException {
		try (Connection connection = DriverManager.getConnection(url);
			 Statement preparedStatement = connection.createStatement();
			 ResultSet set = preparedStatement.executeQuery("SELECT id, title FROM movies")) {
			List<Movie> movieArrayList = new ArrayList<>();
			while (set.next()) {
				Movie movie = new Movie();
				movie.setId(set.getLong("id"));
				movie.setTitle(set.getString("title"));
				movieArrayList.add(movie);
			}
			return movieArrayList;
		}
	}

	@Override
	public void saveOne(Movie movie) throws SQLException {
		if (movie.getId() == null) {
			try (Connection connection = DriverManager.getConnection(url);
				 PreparedStatement preparedStatement = connection.prepareStatement(
						 "INSERT INTO MOVIES (title) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setString(1, movie.getTitle());
				preparedStatement.executeUpdate();
				try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
					generatedKeys.next();
					long id = generatedKeys.getLong(1);
					movie.setId(id);
				}
			}
		} else {
			// UPDATE
			throw new IllegalStateException("Not implemented");
		}
	}

	@Override
	public long count() throws SQLException {
		try (Connection connection = DriverManager.getConnection(url);
			 Statement preparedStatement = connection.createStatement();
			 ResultSet set = preparedStatement.executeQuery("SELECT COUNT(*) FROM MOVIES")) {
			set.next();
			return set.getLong(1);
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
