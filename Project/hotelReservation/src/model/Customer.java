package model;

import java.util.regex.Pattern;

public class Customer {
    String firstName;
    String lastName;
    String email;

    public Customer (String firstName, String lastName, String email) {
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Email format wrong!");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer First name: " + firstName + ", last name: " + lastName + ", email: " + email;
    }
}
