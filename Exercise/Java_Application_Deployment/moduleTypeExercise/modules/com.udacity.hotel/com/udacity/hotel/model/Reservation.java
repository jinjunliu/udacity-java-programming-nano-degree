package com.udacity.hotel.model;

import java.time.LocalDate;
import java.util.Objects;

public class Reservation {
	private String hotelName;
	private String roomType;
	private int roomNumber;
	private String hotelAddress;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private final String generalManager;

	public Reservation(String hotelName, String roomType, int roomNumber, String hotelAddress, LocalDate checkInDate, LocalDate checkOutDate, String generalManager) {
		this.hotelName = hotelName;
		this.roomType = roomType;
		this.roomNumber = roomNumber;
		this.hotelAddress = hotelAddress;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.generalManager = generalManager;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Reservation that = (Reservation) o;
		return roomNumber == that.roomNumber &&
			Objects.equals(hotelName, that.hotelName) &&
			Objects.equals(roomType, that.roomType) &&
			Objects.equals(hotelAddress, that.hotelAddress) &&
			Objects.equals(checkInDate, that.checkInDate) &&
			Objects.equals(checkOutDate, that.checkOutDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(hotelName, roomType, roomNumber, hotelAddress, checkInDate);
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	private String getGeneralManager() {
		return generalManager;
	}
}
