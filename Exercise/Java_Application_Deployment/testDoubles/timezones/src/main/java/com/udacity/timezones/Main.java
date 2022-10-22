package com.udacity.timezones;

import com.udacity.timezones.client.WorldTimeApiHttpClient;
import com.udacity.timezones.service.TimeZoneService;

public class Main {
	public static void main(String[] args) {
		TimeZoneService timeZoneService = new TimeZoneService(new WorldTimeApiHttpClient());
		System.out.println(timeZoneService.getAvailableTimezoneText("Europe"));
	}
}
