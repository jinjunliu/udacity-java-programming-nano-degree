package service;

import model.*;
import java.util.*;

public class ReservationService {
    private final static List<Map<String, IRoom>> rooms = new ArrayList<>();
    private final static List<Reservation> reservations = new ArrayList<>();

    public void addRoom(IRoom room) {
        rooms.add(Collections.singletonMap(room.getRoomNumber(), room));
    }

    public IRoom getARoom(String roomId) {
        for (Map<String, IRoom> roomMap : rooms) {
            if (roomMap.containsKey(roomId)) {
                return roomMap.get(roomId);
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> availableRooms = new ArrayList<>();
        // room is available when it is not reserved within the date range
        for (Map<String, IRoom> room : rooms) {
            for (IRoom roomItem : room.values()) {
                if (isRoomAvailable(roomItem, checkInDate, checkOutDate)) {
                    availableRooms.add(roomItem);
                }
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