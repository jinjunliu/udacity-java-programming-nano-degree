package api;

import model.*;
import service.*;
import java.util.*;

public class HotelResource {
    public static Customer getCustomer(String email) {
        CustomerService customerService = new CustomerService();
        return customerService.getCustomer(email);
    }

    public static void createACustomer(String email, String firstName, String lastName) {
        CustomerService customerService = new CustomerService();
        customerService.addCustomer(firstName, lastName, email);
    }

    public IRoom getRoom(String roomNumber) {
        ReservationService reservationService = new ReservationService();
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        // TODO
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail) {
        // TODO
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        // TODO
    }
}
