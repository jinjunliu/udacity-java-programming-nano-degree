package com.udacity.spotbugs;

import java.util.List;
import java.util.Objects;

public class SpotBugsErrors {

	private String url = "www.udacity.com";

	private long number;

	public void method() {

		List<String> stringList = List.of("one", "two", "three", "four", "five");

		String result = "";

		for (int i = 0; i < stringList.size(); i++) {
			result = result + stringList.get(0);
		}

		System.out.println(url);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SpotBugsErrors that = (SpotBugsErrors) o;
		return number == that.number &&
			Objects.equals(url, that.url);
	}
}
