package service;

import model.*;
import java.util.*;

public class ReservationService {
    List<IRoom> roomList = new ArrayList<>();
    Map<String, IRoom> mapOfRoom = new HashMap<>();
    List<Reservation> reservationList = new ArrayList<>();
    public void addRoom(IRoom room) {
        this.roomList.add(room);
        this.mapOfRoom.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId) {
        return this.mapOfRoom.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservationList.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> rooms = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            if (reservation.getCheckInDate() == checkInDate && reservation.getCheckOutDate() == checkOutDate) {
                rooms.add(reservation.getRoom());
            }
        }
        return rooms;
    }
}