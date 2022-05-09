package org.diligentsnail.jdbc;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {
	/**
	 * Найти фильм по {@code id}
	 *
	 * @param id для поиска
	 * @return {@link Optional} с найденным фильмом или пустой {@link Optional}, если фильма с {@code id} нет
	 */
	Optional<Movie> findById(long id);

	/**
	 * Найти все фильмы
	 *
	 * @return список со всеми найденными фильмами
	 */
	List<Movie> findAll();

	/**
	 * Сохранить фильм.
	 * <p>
	 * Метод устанавливает сгенерированный id вызовом {@link Movie#setId(Long)}.
	 * <p>
	 * Пример:
	 * <pre>
	 * 		Movie movie = new Movie();
	 * 		movie.setTitle("Fight Club");
	 * 		// movie.getId(); возвращает null, потому что ещё не сохранили
	 * 		bookRepository.saveOne(movie);
	 * 		// movie.getId(); возвращает не null, потому что метод saveOne установил id, который сгенерировала БД
	 * </pre>
	 *
	 * @param movie для сохранения
	 * @throws IllegalArgumentException если {@code movie == null}
	 * @throws IllegalArgumentException если {@code movie.getId() != null}
	 */
	void saveOne(Movie movie);
}
