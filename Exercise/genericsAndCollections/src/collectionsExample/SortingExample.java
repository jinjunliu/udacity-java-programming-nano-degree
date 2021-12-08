package collectionsExample;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SortingExample {
    public static void main(String[] args) {
        List<String> names = new LinkedList<String>();
        names.add("Mike");
        names.add("Bob");
        names.add("Alice");

        for (String name: names) {
            System.out.println(name);
        }

        Collections.sort(names);

        for (String name: names) {
            System.out.println(name);
        }
    }
}
