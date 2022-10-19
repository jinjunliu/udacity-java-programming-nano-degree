package com.udacity.packagesearch.search;

import com.udacity.hotel.model.Reservation;
import com.udacity.packagesearch.search.model.PackageSearch;
import com.udacity.packagesearch.search.service.PackageSearchService;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main {
	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
		PackageSearchService packageSearchService = new PackageSearchService();

		LocalDate date = LocalDate.of(2020, 11, 23);
		String departure = "SFO";
		String arrival = "LAX";

		PackageSearch packageSearch = packageSearchService.packageSearch(departure, arrival, date);

		List<Reservation> availableRooms = packageSearch.getAvailableRooms();

		System.out.printf("Found %d available rooms%n", availableRooms.size());


		Optional<Reservation> first = availableRooms.stream().findFirst();

		if (first.isPresent()) {
			Reservation reservation = first.get();

			Field privateStringField = Reservation.class.getDeclaredField("generalManager");

			privateStringField.setAccessible(true);

			String managersName = (String) privateStringField.get(reservation);

			System.out.println(managersName);
		}
	}
}
