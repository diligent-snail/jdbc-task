package org.diligentsnail.jdbc;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class StudentSelector {
	public static void main(String[] args) {
		List<String> students = List.of(
				"Всеволод",
				"Артём",
				"Иван",
				"Станислава",
				"Павел"
				);
		System.out.println(students.get(new Random().nextInt(students.size())));
	}
}
