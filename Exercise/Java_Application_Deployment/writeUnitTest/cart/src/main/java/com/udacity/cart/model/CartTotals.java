package com.udacity.cart.model;

import java.util.Objects;

public class CartTotals {
	private double subtotal;
	private double taxes;

	public CartTotals() {
	}

	public CartTotals(double subtotal, double taxes) {
		this.subtotal = subtotal;
		this.taxes = taxes;
	}

	public void sumItem(CartItem cartItem) {
		subtotal += cartItem.getPrice();
		taxes += cartItem.getTax();
	}

	public CartTotals combine(CartTotals other) {
		this.subtotal += other.subtotal;
		this.taxes += other.taxes;
		return this;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public double getTaxes() {
		return taxes;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CartTotals that = (CartTotals) o;
		return Double.compare(that.subtotal, subtotal) == 0 &&
			Double.compare(that.taxes, taxes) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(subtotal, taxes);
	}

	@Override
	public String toString() {
		return "CartTotals{" +
			"subtotal=" + subtotal +
			", taxes=" + taxes +
			'}';
	}
}
