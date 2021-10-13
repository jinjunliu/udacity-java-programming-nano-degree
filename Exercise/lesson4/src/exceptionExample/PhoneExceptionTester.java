package exceptionExample;

public class PhoneExceptionTester {
    public static void main(String[] args) {
        String[] numbers = new String[] {"123-4567", null, "234-4567", "345-5678"};
        for (String num : numbers) {
            try {
                System.out.println(new Phone("iPhone", num));
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        }

        for (String num: numbers) {
            System.out.println(new Phone("iPhone", num));
        }
    }
}
