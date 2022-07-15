public class App {
    public static void main(String[] args) throws Exception {
        BinaryOperation add = (a, b) -> a + b;
        assert 5 == add.apply(2, 3);
    }
}
