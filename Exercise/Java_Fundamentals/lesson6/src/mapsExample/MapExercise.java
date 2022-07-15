package mapsExample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapExercise {
    public static void main(String[] args) {
        Map<String, Person> mapOfPeople = new HashMap<String, Person>();
        List<Person> people = new ArrayList<Person>();
        Person mike = new Person("Mike", "mike@example.com");
        Person shaun = new Person("Shaun", "shaun@example.com");
        Person sally = new Person("sally", "sally@example.com");
        Person cesar = new Person("cesar", "cesar@example.com");

        people.add(mike);
        people.add(shaun);
        people.add(sally);
        people.add(cesar);

        for (Person person: people) {
            mapOfPeople.put(person.getEmail(), person);
        }

        System.out.println(mapOfPeople.get("sally@example.com"));

        for (String email: mapOfPeople.keySet()) {
            System.out.println(email);
        }

        for (Person person: mapOfPeople.values()) {
            System.out.println(person);
        }
    }
}
