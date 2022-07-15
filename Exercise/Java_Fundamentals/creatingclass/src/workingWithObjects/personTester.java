package workingWithObjects;

public class personTester {
    public static void main (String[] args) {
        Person bob = new Person("Bob", "Johnson");
        Person mike = new Person("Mike", "Liu");
        System.out.println(bob.getFirstName());
        System.out.println(bob);
        System.out.println(mike);
    }
}
