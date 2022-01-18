package service;

import model.Customer;
import java.util.*;

public class CustomerService {
    List<Customer> listOfCustomer = new ArrayList<Customer>();
    Map<String, Customer> mapOfCustomer = new HashMap<>();
    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        this.listOfCustomer.add(customer);
        this.mapOfCustomer.put(email, customer);
    }

    public Customer getCustomer(String customerEmail) {
        return this.mapOfCustomer.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {
        return this.listOfCustomer;
    }
}