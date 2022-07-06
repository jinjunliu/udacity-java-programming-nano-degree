import api.*;
import java.util.*;


public class MainMenu {
    public static void main(String[] args) {
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
        int menuOption = 0;
        while (true) {
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
                case 4 -> admin();
                case 5 -> {
                    System.out.println("Thank you for using our application!");
                    System.exit(0);
                }
                default -> System.out.println("Please enter a valid number (1-5) for the menu option");
            }
        }
    }

    private static void findAndReserveARoom() {
        System.out.println("Please enter your check in date (yyyy-mm-dd):");
        int menuOption = 0;
        while (true) {
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
                case 1 -> findARoom();
                case 2 -> seeAllRooms();
                case 3 -> seeAllReservations();
                case 4 -> {
                    System.out.println("Back to main menu");
                    return;
                }
                case 5 -> {
                    System.out.println("Thank you for using our application!");
                    System.exit(0);
                }
                default -> System.out.println("Please enter a valid number (1-5) for the menu option");
            }
        }
    }
}
