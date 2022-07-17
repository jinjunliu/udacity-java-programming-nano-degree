public final class Calculate {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: Calculate [int] [operator] [int]");
            return;
        }

        Calculator calculator = new Calculator();
        // TODO: Register the four "basic" binary operators: +, -, *, /
        calculator.register("+", (a, b) -> a + b);
        calculator.register("-", (a, b) -> a - b);
        calculator.register("*", (a, b) -> a * b);
        calculator.register("/", (a, b) -> a / b);
        int a = Integer.parseInt(args[0]);
        String operator = args[1];
        int b = Integer.parseInt(args[2]);

        System.out.println(calculator.calculate(a, operator, b));
    }
}
