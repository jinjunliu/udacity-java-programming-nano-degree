package com.udacity.timezones.service;

import com.udacity.timezones.client.ChargeUserApiHttpClient;
import com.udacity.timezones.model.TicketItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public class ChargeService {
	private final ChargeUserApiHttpClient chargeUserApiHttpClient;

	public ChargeService(ChargeUserApiHttpClient chargeUserApiHttpClient) {
		this.chargeUserApiHttpClient = chargeUserApiHttpClient;
	}

	public boolean chargeUser(String userId, List<TicketItem> items, BigDecimal tip, BigDecimal discount) {
		BiConsumer<TicketItem, TicketItem> accumulator = (item1, item2) -> {
			BigDecimal price = Optional.ofNullable(item1.getPrice()).orElse(BigDecimal.ZERO);
			BigDecimal tax = Optional.ofNullable(item1.getTax()).orElse(BigDecimal.ZERO);

			item1.setPrice(price.add(item2.getPrice()));
			item1.setTax(tax.add(item2.getTax()));
		};

		TicketItem totalsItem = items.stream().collect(TicketItem::new, accumulator, accumulator);

		return chargeUserApiHttpClient.charge(
			userId,
			totalsItem.getPrice()
				.add(totalsItem.getTax())
				.add(tip)
				.subtract(discount)
		);
	}
}
