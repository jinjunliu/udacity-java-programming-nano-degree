package service;

import model.*;
import java.util.*;

public class ReservationService {
    final static Map<String, IRoom> rooms = new HashMap<>();
    final static List<Reservation> reservations = new ArrayList<>();

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId) {
        if (rooms.containsKey(roomId)) {
            return rooms.get(roomId);
        }
        return null;
    }

    public Collection<IRoom> getAllRooms() {
        return rooms.values();
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> availableRooms = new ArrayList<>();
        // room is available when it is not reserved within the date range
        for (IRoom room : rooms.values()) {
            if (isRoomAvailable(room, checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    private boolean isRoomAvailable(IRoom room, Date checkInDate, Date checkOutDate) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().equals(room) &&
                reservation.getCheckInDate().before(checkInDate) && reservation.getCheckOutDate().after(checkInDate)) {
                return false;
            }
            if (reservation.getRoom().equals(room) &&
                reservation.getCheckInDate().before(checkOutDate) && reservation.getCheckOutDate().after(checkOutDate)) {
                return false;
            }
        }
        return true;
    }

    private Collection<IRoom> recommendRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> recommendedRooms = new ArrayList<>();
        // recommend rooms that add seven days to the check in date and check out date
        for (IRoom room : rooms.values()) {
            if (isRoomAvailable(room, addDays(checkInDate, 7), addDays(checkOutDate, 7))) {
                recommendedRooms.add(room);
            }
        }
        return recommendedRooms;
    }

    private Date addDays(Date checkInDate, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkInDate);
        calendar.add(Calendar.DATE, i);
        return calendar.getTime();
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        Collection<Reservation> customerReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
    }

    public void printAllReservations() {
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }
}
