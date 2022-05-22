package org.diligentsnail.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Запустите main, в выводе должна быть цифра «1».
 * <p>
 * Если это не так, то откройте вкладку с Maven в Intellij IDEA и попробуйте обновить конфигурацию maven
 */
public class Main {

	// 1. Сегодня
	// 2. 28
	// 3. 4.06 -- очная


	// 1. JDBC?


	// 1. Embedded
	// 2. Network Server


	public static void main(String[] args) throws SQLException {
		JdbcMovieRepository jdbcMovieRepository = new JdbcMovieRepository(
				"jdbc:derby:/tmp/movies;create=true");
		List<Movie> movies = List.of(
				new Movie("Fight Club"),
				new Movie("Interstellar"),
				new Movie("Pulp Fiction"),
				new Movie("The Matrix"),
				new Movie("The Lion King"),
				new Movie("The Shawshank Redemption")
		);
		for (Movie movie : movies) {
			jdbcMovieRepository.saveOne(movie);
		}
/*
		try (Connection ignored = DriverManager.getConnection("jdbc:derby:;shutdown=true")) {

		} catch (SQLException e) {
		}
*/
	}
}
