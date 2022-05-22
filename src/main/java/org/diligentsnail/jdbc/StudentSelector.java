package org.diligentsnail.jdbc;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StudentSelector {
	public static void main(String[] args) {
		List<String> students = List.of(
				// "Катя",
				"Анна Словягина",
				// "Дмитрий",
				"Марина"
//				"Софья",
				// "Анна Сухова"
				// "Филипп"

		);

		System.out.println(students.get(ThreadLocalRandom.current().nextInt(students.size())));
	}
}
