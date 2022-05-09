package org.diligentsnail.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Запустите main, в выводе должна быть цифра «1».
 * <p>
 * Если это не так, то откройте вкладку с Maven в Intellij IDEA и попробуйте обновить конфигурацию maven
 */
public class Main {
	public static void main(String[] args) throws SQLException {
		try (Connection connection = DriverManager.getConnection("jdbc:derby:memory:sample;create=true");
			 Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("select 1 from sysibm.sysdummy1")) {
			if (!resultSet.next()) {
				throw new IllegalStateException("Empty result set");
			}
			int i = resultSet.getInt(1);
			System.out.println(i);
		}
	}
}
