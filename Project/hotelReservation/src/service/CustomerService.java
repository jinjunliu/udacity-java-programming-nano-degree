package service;

import model.Customer;
import java.util.*;

public class CustomerService {
    private static final List<Map<String, Customer>> customers = new ArrayList<>();
    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        Map <String, Customer> customerMap = new HashMap<>();
        customerMap.put(email, customer);
        customers.add(customerMap);
    }

    public Customer getCustomer(String customerEmail) {
        for (Map<String, Customer> customerMap : customers) {
            if (customerMap.containsKey(customerEmail)) {
                return customerMap.get(customerEmail);
            }
        }
        return null;
    }

    public Collection<Customer> getAllCustomers() {
        Collection<Customer> allCustomers = new ArrayList<>();
        for (Map<String, Customer> customerMap : customers) {
            allCustomers.addAll(customerMap.values());
        }
        return allCustomers;
    }
}
