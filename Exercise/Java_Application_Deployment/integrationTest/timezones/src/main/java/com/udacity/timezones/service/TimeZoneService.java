package com.udacity.timezones.service;

import com.udacity.timezones.client.WorldTimeApiHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class TimeZoneService {
	private final WorldTimeApiHttpClient worldTimeApiRestClient;

	public TimeZoneService(String baseUrl) {
		Retrofit retrofit = new Retrofit.Builder()
			.baseUrl(baseUrl)
			.addConverterFactory(GsonConverterFactory.create())
			.build();

		this.worldTimeApiRestClient = retrofit.create(WorldTimeApiHttpClient.class);
	}

	public String getAvailableTimezoneText(String area) {
		try {
			Call<List<String>> validTimeZonesCall = worldTimeApiRestClient.getValidTimeZones(area);
			List<String> validTimeZones = validTimeZonesCall.execute().body();
			if (validTimeZones != null) {
				return String.format(
					"Available timezones in %s are %s.",
					area,
					String.join(", ", validTimeZones)
				);
			}
			return "No available timezones.";
		} catch (IOException e) {
			throw new RuntimeException("Failed to get timezones", e);
		}
	}
}
