public class App {
    public static void main(String[] args) throws Exception {
        BinaryOperation add = (a, b) -> a + b;
        System.out.println(add.apply(1, 2));
        assert 5 == add.apply(2, 3);
    }
}
