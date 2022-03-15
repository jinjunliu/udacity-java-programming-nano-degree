import api.*;
import model.*;
import service.*;
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
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter a string");
            String userInput = scanner.nextLine();
            System.out.println("User input: " + userInput);
        } catch (Exception ex) {
            ex.getLocalizedMessage();
        }
    }
}
