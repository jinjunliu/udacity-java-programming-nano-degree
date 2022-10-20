package com.udacity.cart.model;

import java.util.Objects;

public class CartItem {
	private String name;
	private double price;
	private double tax;

	public CartItem(String name, double price, double tax) {
		this.name = name;
		this.price = price;
		this.tax = tax;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CartItem cartItem = (CartItem) o;
		return Double.compare(cartItem.getPrice(), getPrice()) == 0 &&
			Double.compare(cartItem.getTax(), getTax()) == 0 &&
			Objects.equals(getName(), cartItem.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), getPrice(), getTax());
	}

	@Override
	public String toString() {
		return "CartItem{" +
			"name='" + name + '\'' +
			", cost=" + price +
			", tax=" + tax +
			'}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}
}
