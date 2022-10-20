package com.udacity.cart.service;

import com.udacity.cart.model.CartItem;
import com.udacity.cart.model.CartTotals;
import com.udacity.cart.model.User;
import com.udacity.cart.model.UserType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TotalsWithDiscountCalculatorTest {

	static User globalUser;
	TotalsWithDiscountCalculator totalsWithDiscountCalculator;

	@BeforeAll
	static void setupGlobalUser() {
		System.out.println("Setting up a global User");
		globalUser = new User("Global User", UserType.REGULAR, 100.00);
	}

	@BeforeEach
	void setupCalculator() {
		System.out.println("Setting up a new calculator");
		totalsWithDiscountCalculator = new TotalsWithDiscountCalculator(globalUser);
	}

	@RepeatedTest(3)
	void getTotalsWithDiscounts_userWithCredit_chargedMultipleTimes(RepetitionInfo repetitionInfo) {
		totalsWithDiscountCalculator.getTotals(List.of(new CartItem("Twenty dollar item", 20.0, 0)));
		assertEquals(100.0 - 20 * repetitionInfo.getCurrentRepetition(), globalUser.getCredit());
	}


	@ParameterizedTest
	@MethodSource("differentUserTypesAndExpectedTotals")
	void getTotalsWithDiscounts_regularAndPlatinumUser_returnsDifferentSubtotal(
		User user,
		CartTotals expectedCartTotals
	) {
		TotalsWithDiscountCalculator totalsWithDiscountCalculator = new TotalsWithDiscountCalculator(user);

		CartTotals totals = totalsWithDiscountCalculator.getTotals(List.of(new CartItem("Ten dollar item", 10.0, 1.0)));

		assertEquals(expectedCartTotals, totals);

	}

	private static Stream<Arguments> differentUserTypesAndExpectedTotals() {
		return Stream.of(
			Arguments.of(
				new User("Regular User", UserType.REGULAR, 0.0),
				new CartTotals(10.0, 1.0)
			),
			Arguments.of(
				new User("Platinum User", UserType.PLATINUM, 0.0),
				new CartTotals(9.0, 1.0)
			)
		);
	}
}