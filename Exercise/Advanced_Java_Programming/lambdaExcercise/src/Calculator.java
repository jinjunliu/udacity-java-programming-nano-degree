import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

public final class Calculator {
    private final Map<String, BinaryOperator<Integer>> binaryOperators = new HashMap<>();

    public void register(String operator, BinaryOperator<Integer> function) {
        binaryOperators.put(operator.strip(), function);
    }

    public int calculate(int a, String operator, int b) {
        BinaryOperator<Integer> function = binaryOperators.get(operator);
        if (function == null) {
            throw new IllegalArgumentException("Unknown operator: " + operator);
        }
        return function.apply(a, b);
    }
}
