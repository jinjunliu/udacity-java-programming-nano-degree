package service;

import model.Customer;
import java.util.*;

public class CustomerService {
    // singleton instance
    // ref: https://www.geeksforgeeks.org/singleton-class-java/
    private static CustomerService instance = null;
    public Map<String, Customer> customers;
    private CustomerService() {
        customers = new HashMap<>();
    }

    public static CustomerService CustomerService() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        customers.put(email, customer);
        System.out.println(customer);
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
