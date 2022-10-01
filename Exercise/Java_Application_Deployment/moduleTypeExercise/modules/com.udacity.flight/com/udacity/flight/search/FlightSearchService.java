package com.udacity.flight.search;

import com.udacity.flight.model.Flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class FlightSearchService {

	public List<Flight> findAvailableFlights(String departureAirport, String arrivalAirport) {
		return List.of(
			new Flight(
				"123", "SFO", "LAX",
				ZonedDateTime.of(LocalDateTime.of(2020, 11, 23, 10, 30), ZoneId.of("America/Los_Angeles")),
				ZonedDateTime.of(LocalDateTime.of(2020, 11, 23, 11, 50), ZoneId.of("America/Los_Angeles"))
			),
			new Flight(
				"123", "SFO", "LAX",
				ZonedDateTime.of(LocalDateTime.of(2020, 11, 24, 12, 30), ZoneId.of("America/Los_Angeles")),
				ZonedDateTime.of(LocalDateTime.of(2020, 11, 24, 13, 50), ZoneId.of("America/Los_Angeles"))
			),
			new Flight(
				"123", "SFO", "LAX",
				ZonedDateTime.of(LocalDateTime.of(2020, 11, 23, 14, 30), ZoneId.of("America/Los_Angeles")),
				ZonedDateTime.of(LocalDateTime.of(2020, 11, 23, 15, 50), ZoneId.of("America/Los_Angeles"))
			)
		);
	}
}
