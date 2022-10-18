package com.udacity.packagesearch.search.model;

import com.udacity.flight.model.Flight;
import com.udacity.hotel.model.Reservation;

import java.util.List;

public class PackageSearch {

	private final List<Flight> availableFlights;
	private final List<Reservation> availableRooms;

	public PackageSearch(List<Flight> availableFlights, List<Reservation> availableRooms) {
		this.availableFlights = availableFlights;
		this.availableRooms = availableRooms;
	}

	public List<Flight> getAvailableFlights() {
		return availableFlights;
	}

	public List<Reservation> getAvailableRooms() {
		return availableRooms;
	}
}
