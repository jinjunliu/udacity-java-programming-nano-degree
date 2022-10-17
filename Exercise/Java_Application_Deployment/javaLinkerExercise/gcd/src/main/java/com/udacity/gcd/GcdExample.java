package com.udacity.gcd;

import java.math.BigInteger;


public class GcdExample {

	public static void main(String[] args) {
		BigInteger thirtySix = new BigInteger("36");
		BigInteger twentyFour = new BigInteger("24");

		BigInteger result = thirtySix.gcd(twentyFour);

		System.out.printf("Greatest common divisor of %s and %s is %s%n", thirtySix, twentyFour, result);
	}
}
