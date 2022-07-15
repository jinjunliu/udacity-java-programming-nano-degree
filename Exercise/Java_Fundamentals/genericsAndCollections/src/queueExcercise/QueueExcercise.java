package queueExcercise;

import java.util.LinkedList;
import java.util.Queue;

public class QueueExcercise {
    public static void main(String[] args) {
        Queue<String> queuedCustomers = new LinkedList<String>();

        queuedCustomers.add("1234");
        queuedCustomers.add("3412"); // customer 3412 takes a number
        queuedCustomers.add("0123"); // customer 0123 takes a number
        queuedCustomers.add("4321"); // customer 4321 takes a number

        while (!queuedCustomers.isEmpty()) {
            System.out.println("Customer " + queuedCustomers.poll() + " is getting helped.");
        }

    }
}
