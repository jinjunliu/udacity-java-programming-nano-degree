package api;

import model.*;
import java.util.*;
import service.*;

public class AdminResource extends HotelResource {
    public static void addRoom(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            reservationService.addRoom(room);
        }
    }

    public static Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    public static Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public static void displayAllReservations() {
        reservationService.printAllReservations();
    }
}
