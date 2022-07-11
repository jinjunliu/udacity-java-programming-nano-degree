package api;

import model.*;
import java.util.*;

public class AdminResource extends HotelResource {
    public static void addRoom(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            reservationService.addRoom(room);
        }
        System.out.println("Rooms added successfully");
        System.out.println("Added rooms:");
        for (IRoom room : rooms) {
            System.out.println(room.toString());
        }
    }

    public static Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    public static Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public static void displayAllCustomers() {
        if (getAllCustomers().isEmpty()) {
            System.out.println("No customers found");
        } else {
            System.out.println("Customers:");
            for (Customer customer : getAllCustomers()) {
                System.out.println(customer.toString());
            }
        }
    }

    public static void displayAllRooms() {
        if (getAllRooms().isEmpty()) {
            System.out.println("No rooms found");
        } else {
            for (IRoom room : getAllRooms()) {
                System.out.println(room.toString());
            }
        }
    }

    public static void displayAllReservations() {
        reservationService.printAllReservations();
    }
}
