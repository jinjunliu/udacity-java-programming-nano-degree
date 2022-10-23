package com.udacity.timezones.model;

import java.math.BigDecimal;
import java.util.Objects;

public class TicketItem {
	private String name;
	private BigDecimal price;
	private BigDecimal tax;

	public TicketItem() {
	}

	public TicketItem(String name, BigDecimal price, BigDecimal tax) {
		this.name = name;
		this.price = price;
		this.tax = tax;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TicketItem that = (TicketItem) o;
		return Objects.equals(getName(), that.getName()) &&
			Objects.equals(getPrice(), that.getPrice()) &&
			Objects.equals(getTax(), that.getTax());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), getPrice(), getTax());
	}

	@Override
	public String toString() {
		return "TicketItem{" +
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
}
