package com.udacity.cart.service;

import com.udacity.cart.model.CartItem;
import com.udacity.cart.model.CartTotals;

import java.util.List;
import java.util.stream.Collector;

public class TotalsCalculator {

	public CartTotals getTotals(List<CartItem> items) {
		return items.stream()
			.collect(
				Collector.of(
					CartTotals::new,
					CartTotals::sumItem,
					CartTotals::combine
				)
			);
	}
}
