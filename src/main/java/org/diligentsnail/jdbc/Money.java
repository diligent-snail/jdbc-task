package org.diligentsnail.jdbc;

// Value Object
public class Money {
	private String currency;
	private int amount;

	public Money(String currency, int amount) {
		this.currency = currency;
		this.amount = amount;
	}

	public static void main(String[] args) {
		Money money1 = new Money("USD", 100);
		Money money2 = new Money("USD", 100);
		// true
		System.out.println(money1.equals(money2));
		Point zero0 = new Point(0, 0);
		Point zero1 = new Point(0, 0);
		// true
		System.out.println(zero0.equals(zero1));

		Person josh1 = new Person("Josh");
		Person josh2 = new Person("Josh");
		System.out.println(josh1.equals(josh2));
	}

	// Entity, DDD
	public static class Person {
		// Surrogate key
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
