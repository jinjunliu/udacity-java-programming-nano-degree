import api.*;
import java.util.*;
import model.*;


public class MainMenu {
    static AdminResource resource = new AdminResource();
    public static void mainMenu() {
        int menuOption = 0;
        while (true) {
            System.out.println("""
                Welcome to hotel reservation application!
                ------------------------------
                1. Find and reserve a room;
                2. See my reservations;
                3. Create an account;
                4. Admin;
                5. Exit.
                -------------------------------
                Please select a number for the menu option""");
            while (true) {
                Scanner scanner = new Scanner(System.in);
                try {
                    menuOption = scanner.nextInt();
                    if (1 <= menuOption && menuOption <= 5) {
                        System.out.println("You selected menu option " + menuOption);
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Please enter a valid number (1-5) for the menu option");
                }
            }
            switch (menuOption) {
                case 1 -> findAndReserveARoom();
                case 2 -> seeMyReservations();
                case 3 -> createAnAccount();
                case 4 -> AdminMenu.admin();
                case 5 -> {
                    System.out.println("Thank you for using our application!");
                    System.exit(0);
                }
                default -> System.out.println("Please enter a valid number (1-5) for the menu option");
            }
        }
    }

    private static String emailInput() {
        System.out.println("Please enter your email address");
        String email = "";
        while (true) {
            Scanner scanner = new Scanner(System.in);
            email = scanner.nextLine();
            if (email.matches("^(.+)@(.+).(.+)$")) {
                break;
            } else {
                System.out.println("Please enter a valid email address");
            }
        }
        return email;
    }

    private static void findAndReserveARoom() {
        String email = emailInput();
        // check if customer exists
        Customer customer = resource.getCustomer(email);
        if (customer == null) {
            System.out.println("Customer does not exist. Please create an account");
            createAnAccount();
        } else {
            System.out.println("Please enter your check in date (yyyy-mm-dd):");
            Date checkIn;
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String checkInDate = scanner.nextLine();
                // to date
                try {
                    checkIn = HotelResource.parseDate(checkInDate);
                    System.out.println("You entered check in date: " + checkIn);
                    break;
                } catch (Exception e) {
                    System.out.println("Please enter a valid date (yyyy-mm-dd)");
                }
            }
            System.out.println("Please enter your check out date (yyyy-mm-dd):");
            Date checkOut;
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String checkOutDate = scanner.nextLine();
                // to date
                try {
                    checkOut = HotelResource.parseDate(checkOutDate);
                    System.out.println("You entered check out date: " + checkOut);
                    break;
                } catch (Exception e) {
                    System.out.println("Please enter a valid date (yyyy-mm-dd)");
                }
            }
            // find available rooms
            Collection<IRoom> rooms = resource.findARoom(checkIn, checkOut);
            int numberOfRooms = rooms.size();
            // display all found rooms
            if (numberOfRooms > 0) {
                System.out.println("Found " + numberOfRooms + " rooms:");
                for (IRoom room : rooms) {
                    System.out.println(room.toString());
                }
            } else {
                System.out.println("No rooms available");
                System.out.println("Recommend rooms:");
                rooms = resource.recommendRooms(checkIn, checkOut);
                for (IRoom room : rooms) {
                    System.out.println(room.toString());
                }
            }
            // select a room
            System.out.println("Please enter a room number:");
            String roomNumber = "";
            while (true) {
                Scanner scanner = new Scanner(System.in);
                roomNumber = scanner.nextLine();
                String finalRoomNumber = roomNumber;
                if (rooms.stream().anyMatch(room -> room.getRoomNumber().equals(finalRoomNumber))) {
                    break;
                } else {
                    System.out.println("Please enter a valid room number");
                }
            }
            // book a room
            Reservation reservation = resource.bookARoom(email, resource.getRoom(roomNumber), checkIn, checkOut);
            System.out.println("Your reservation is: " + reservation.toString());
        }
    }

    private static void createAnAccount() {
        System.out.println("Please enter your first name");
        String firstName = "";
        while (true) {
            Scanner scanner = new Scanner(System.in);
            firstName = scanner.nextLine();
            if (firstName.matches("^[a-zA-Z]+$")) {
                break;
            } else {
                System.out.println("Please enter a valid first name");
            }
        }
        System.out.println("Please enter your last name");
        String lastName = "";
        while (true) {
            Scanner scanner = new Scanner(System.in);
            lastName = scanner.nextLine();
            if (lastName.matches("^[a-zA-Z]+$")) {
                break;
            } else {
                System.out.println("Please enter a valid last name");
            }
        }
        String email = emailInput();
        // check if customer exists
        Customer customer = resource.getCustomer(email);
        if (customer == null) {
            resource.createACustomer(email, firstName, lastName);
            System.out.println("Customer created successfully");
        } else {
            System.out.println("Customer already exists");
        }
    }

    private static void seeMyReservations() {
        System.out.println("Please enter your email address");
        String email = emailInput();
        // check if customer exists
        Customer customer = resource.getCustomer(email);
        if (customer == null) {
            System.out.println("Customer does not exist. Please create an account");
            createAnAccount();
        } else {
            System.out.println("Your reservations:");
            Collection<Reservation> reservations = resource.getCustomerReservations(email);
            for (Reservation reservation : reservations) {
                System.out.println(reservation.toString());
            }
        }
    }
}
