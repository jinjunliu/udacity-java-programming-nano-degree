package com.udacity.packagesearch.search.service;

import com.udacity.flight.model.Flight;
import com.udacity.flight.search.FlightSearchService;
import com.udacity.hotel.model.Reservation;
import com.udacity.hotel.search.ReservationSearchService;
import com.udacity.packagesearch.search.model.PackageSearch;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PackageSearchService {

	private final FlightSearchService flightSearchService = new FlightSearchService();
	private final ReservationSearchService reservationSearchService = new ReservationSearchService();

	public PackageSearch packageSearch(String departure, String arrival, LocalDate date){
		List<Flight> availableFlights = flightSearchService.findAvailableFlights(departure, arrival).stream()
			.filter(it -> it.getArrivalTime().toLocalDate().equals(date))
			.collect(Collectors.toList());
		List<Reservation> availableRooms = reservationSearchService.findAvailableRooms(arrival).stream()
			.filter(it -> it.getCheckInDate().equals(date))
			.collect(Collectors.toList());

		return new PackageSearch(availableFlights, availableRooms);
	}
}
