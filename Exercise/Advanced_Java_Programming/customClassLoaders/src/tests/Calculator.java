package tests;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

public final class Calculator {
  private final Map<String, BinaryOperator<Integer>> operators = new HashMap<>();

  public void registerOperation(String symbol, BinaryOperator<Integer> operator) {
    operators.put(symbol.strip(), operator);
  }

  public int calculate(int a, String operator, int b) {
    return operators.get(operator).apply(a, b);
  }
}
