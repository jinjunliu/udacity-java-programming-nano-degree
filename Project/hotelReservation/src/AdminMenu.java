import api.*;
import java.util.*;
import model.*;

public class AdminMenu {
    public static void admin() {
        int menuOption = 0;
        while (true) {
            System.out.println("""
                           Admin Menu
                           --------------------------------------
                           1. See all Customers;
                           2. See all Rooms;
                           3. See all Reservations;
                           4. Add Rooms;
                           5. Back to Main Menu.
                            --------------------------------------
                            """);
            while (true) {
                Scanner scanner = new Scanner(System.in);
                try {
                    menuOption = scanner.nextInt();
                    if (1 <= menuOption && menuOption <= 6) {
                        System.out.println("You selected admin menu option " + menuOption);
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Please enter a valid number (1-6) for the admin menu option");
                }
            }
            switch (menuOption) {
                case 1 -> AdminResource.displayAllCustomers();
                case 2 -> AdminResource.displayAllRooms();
                case 3 -> AdminResource.displayAllReservations();
                case 4 -> AdminResource.addRoom(inputRooms());
                case 5 -> {
                    return; // exit to main menu
                }
            }
        }
    }

    public static List<IRoom> inputRooms() {
        List<IRoom> rooms = new ArrayList<IRoom>();
        int numRooms = 0;
        while (true) {
            System.out.println("Please enter the number of rooms you want to add:");
            Scanner scanner = new Scanner(System.in);
            try {
                numRooms = scanner.nextInt();
                if (numRooms > 0) {
                    break;
                } else if (numRooms == 0) {
                    return rooms;
                } else {
                    System.out.println("Please enter a valid number of rooms");
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid number of rooms");
            }
        }
        for (int i = 0; i < numRooms; i++) {
            System.out.println("Entering room " + (i + 1) + " of " + numRooms + ":");

            System.out.println("Please enter the room number:");
            Scanner scannerRoomNumber = new Scanner(System.in);
            String roomNumber = scannerRoomNumber.nextLine();

            RoomType roomType;
            // room type must be single or double
            while (true) {
                System.out.println("Please enter the room type (single, double):");
                Scanner scannerRoomType = new Scanner(System.in);
                String roomTypeString = scannerRoomType.nextLine();
                if (roomTypeString.equals("single")) {
                    roomType = RoomType.SINGLE;
                    break;
                } else if (roomTypeString.equals("double")) {
                    roomType = RoomType.DOUBLE;
                    break;
                } else {
                    System.out.println("Please enter a valid room type (single, double)");
                }
            }

            double roomPrice = 0;
            while (true) {
                System.out.println("Please enter the room price:");
                Scanner scanner = new Scanner(System.in);
                try {
                    roomPrice = scanner.nextDouble();
                    if (roomPrice >= 0) {
                        break;
                    } else {
                        System.out.println("Please enter a valid room price");
                    }
                } catch (Exception e) {
                    System.out.println("Please enter a valid room price");
                }
            }
            Room room = new Room(roomNumber, roomPrice, roomType);
            rooms.add(room);
        }
        return rooms;
    }
}
