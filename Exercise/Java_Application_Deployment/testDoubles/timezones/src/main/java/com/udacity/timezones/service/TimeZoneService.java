package com.udacity.timezones.service;

import com.udacity.timezones.client.WorldTimeApiHttpClient;

public class TimeZoneService {
	private final WorldTimeApiHttpClient worldTimeApiRestClient;

	public TimeZoneService(WorldTimeApiHttpClient worldTimeApiRestClient) {
		this.worldTimeApiRestClient = worldTimeApiRestClient;
	}

	public String getAvailableTimezoneText(String area) {
		return String.format(
			"Available timezones in %s are %s.",
			area,
			String.join(", ", worldTimeApiRestClient.getValidTimeZones(area))
		);
	}
}
