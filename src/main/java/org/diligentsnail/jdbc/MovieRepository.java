package org.diligentsnail.jdbc;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MovieRepository {
	/**
	 * Найти фильм по {@code id}
	 *
	 * @param id для поиска
	 * @return {@link Optional} с найденным фильмом или пустой {@link Optional}, если фильма с {@code id} нет
	 * @throws SQLException при ошибке доступа к БД
	 */
	Optional<Movie> findById(long id) throws SQLException;

	/**
	 * Найти все фильмы
	 *
	 * @return список со всеми найденными фильмами
	 * @throws SQLException при ошибке доступа к БД
	 */
	List<Movie> findAll() throws SQLException;

	/**
	 * Сохранить или обновить фильм.
	 * <p>
	 * Если {@link Movie#getId()} возвращает {@code null}, то метод вставляет строку в таблицу и устанавливает
	 * сгенерированный id вызовом {@link Movie#setId(Long)}.
	 * Иначе метод обновляет существующую строку в таблице
	 *
	 * @param movie для сохранения
	 * @throws IllegalArgumentException если {@code movie == null}
	 * @throws SQLException при ошибке доступа к БД
	 */
	void saveOne(Movie movie) throws SQLException;

	/**
	 * @return возвращает количество фильмов в репозитории
	 * @throws SQLException при ошибке доступа к БД
	 */
	long count() throws SQLException;

	/**
	 * Найти фильмы, в названии которых есть подстрока {@code substring}
	 *
	 * @param substring для поиска
	 * @return список фильмов, в названии которых есть {@code substring}
	 * @throws SQLException при ошибке доступа к БД
	 */
	List<Movie> findByTitleContaining(String substring) throws SQLException;

	/**
	 * Удалить фильм по {@code id}
	 *
	 * @param id по которому удалять
	 * @throws SQLException при ошибке доступа к БД
	 */
	void deleteById(long id) throws SQLException;
}
