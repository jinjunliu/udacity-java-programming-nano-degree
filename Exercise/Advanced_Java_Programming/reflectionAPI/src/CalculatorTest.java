public final class CalculatorTest implements UnitTest {

  private final Calculator calculator = new Calculator();

  @Override
  public void beforeEachTest() {
    calculator.registerOperation("+", (a, b) -> a + b);
    calculator.registerOperation("-", (a, b) -> a - b);
    calculator.registerOperation("/", (a, b) -> a / b);
    calculator.registerOperation("*", (a, b) -> a * b);
  }

  @Test
  public void testAddition() {
    assert calculator.calculate(1, "+", 1) == 2;
  }

  @Test
  public void testSubtraction() {
    assert calculator.calculate(45, "-", 43) == 1;
  }

  public void testNotActuallyATest() {
    assert 1 == 0;
  }
}
