package com.udacity.flight.model;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Flight {
	private String flightNumber;
	private String departureAirport;
	private String arrivalAirport;
	private ZonedDateTime departureTime;
	private ZonedDateTime arrivalTime;

	public Flight(String flightNumber, String departureAirport, String arrivalAirport, ZonedDateTime departureTime, ZonedDateTime arrivalTime) {
		this.flightNumber = flightNumber;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Flight flight = (Flight) o;
		return getFlightNumber().equals(flight.getFlightNumber()) &&
			getDepartureAirport().equals(flight.getDepartureAirport()) &&
			getArrivalAirport().equals(flight.getArrivalAirport());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getFlightNumber(), getDepartureAirport(), getArrivalAirport());
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public ZonedDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(ZonedDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public ZonedDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(ZonedDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
}
