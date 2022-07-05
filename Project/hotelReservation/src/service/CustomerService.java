package service;

import model.Customer;
import java.util.*;

public class CustomerService {
    private static final Map<String, Customer> customers = new HashMap<>();
    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        customers.put(email, customer);
    }

    public Customer getCustomer(String customerEmail) {
        // if customerEmail is in the keys
        if (customers.containsKey(customerEmail)) {
            return customers.get(customerEmail);
        }
        return null;
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }
}
