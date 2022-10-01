package com.udacity.hotel.search;

import com.udacity.hotel.model.Reservation;

import java.time.LocalDate;
import java.util.List;

public class ReservationSearchService {

	public List<Reservation> findAvailableRooms(String city) {
		return List.of(
			new Reservation(
				"Heel-town", "1 double bed", 201,
				"5711 W Century Blvd", LocalDate.of(2020, 11, 23), LocalDate.of(2020, 11, 24), "Bob"
			),
			new Reservation(
				"Hi-yacht", "1 king bed", 312,
				"6225 W Century Blvd", LocalDate.of(2020, 11, 24), LocalDate.of(2020, 11, 25), "Charlize"
			),
			new Reservation(
				"Revival", "Guest room, 2 queen", 411,
				"9620 Airport Blvd", LocalDate.of(2020, 11, 23), LocalDate.of(2020, 11, 24), "Alice"
			)
		);
	}
}
