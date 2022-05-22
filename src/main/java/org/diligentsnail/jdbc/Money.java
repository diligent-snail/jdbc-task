package org.diligentsnail.jdbc;

import java.util.UUID;

public class Money {
	private final String currency;
	private final long amount;

	public Money(String currency, long amount) {
		this.currency = currency;
		this.amount = amount;
	}

	public static void main(String[] args) {
		// Value Object: Money, Point, String, Optional, Duration, LocalDate
		Money usd0 = new Money("USD", 100);
		Money usd1 = new Money("USD", 100);

		// true
		System.out.println(usd0.equals(usd1));

		"abc".equals(new String("abc"));
		Integer.valueOf(10).equals(Integer.valueOf(10));
		Point point0 = new Point(0, 0);
		Point point2 = new Point(1, 1);
		Point point1 = new Point(0, 0);
		// true
		System.out.println(point0.equals(point1));

		// Entity: Person, Tweet, Message, Order
		Person josh1 = new Person("Josh");
		Person josh2 = new Person("Josh");
		System.out.println(josh1.equals(josh2));
	}


	public static class Person {
		// surrogate key
		private long id;
		private final String name;

		public Person(String name) {
			this.name = name;
		}
	}

	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
