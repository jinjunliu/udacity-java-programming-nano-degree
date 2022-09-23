package com.udacity.gcd;

/**
 * Class for calculating greatest common divisor.
 */
public class GreatestCommonDivisorCalculator {

	/**
	 * This method calculates the greatest common divisor of two numbers ({@code a}  and {@code b}) by using the
	 * Euclid's Algorithm.
	 *
	 * @param a first parameter
	 * @param b second parameter
	 * @return The greatest common divisor of {@code a}  and {@code b}
	 */
	public static long gcd(long a, long b) {
		if (a == 0) {
			return b;
		}

		return gcd(a % b, a);
	}
}
