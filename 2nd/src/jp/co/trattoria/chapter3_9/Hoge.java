package jp.co.trattoria.chapter3_9;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Hoge {

	private static Object getFieldValue(Object target, String fieldName) {

		for (Class<?> clazz = target.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
			Field field;
			try {
				field = clazz.getDeclaredField(fieldName);
				field.setAccessible(true);
				return field.get(target);

			} catch (IllegalAccessException exception) {
				throw new RuntimeException(exception);

			} catch (NoSuchFieldException exception) {

			}
		}
		throw new RuntimeException();
	}

	public static Comparator<Person> lexicographicComparator(String... fieldNames) {
		return (t, u) -> {
			for (String fieldName : fieldNames) {
				Object tf = getFieldValue(t, fieldName);
				Object uf = getFieldValue(u, fieldName);
				if (tf.equals(uf)) {
					continue;
				}
				return ((Comparable) tf).compareTo(uf);
			}
			return 0;
		};
	}

	public static void main(String[] args) {

		List<Person> people = new ArrayList<Person>();
		Person person1 = new Person("Taro", "Yamada");
		Person person2 = new Person("Ichiro", "Suzuki");

		people.add(person1);
		people.add(person2);
		people.add(person1);

		Collections.sort(people, Hoge.lexicographicComparator("lastName", "firstName"));
		System.out.println(people);
	}
}

class Person {
	private String lastName;
	private String firstName;

	Person(String l, String f) {
		this.lastName = l;
		this.firstName = f;
	}

	@Override
	public String toString() {
		return "name=" + lastName + " " + firstName;
	}
}
