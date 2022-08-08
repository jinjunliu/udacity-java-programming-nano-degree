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
  public void testAddition() throws Exception {
    Thread.sleep(3000);
    assert calculator.calculate(1, "+", 1) == 2;
  }

  @Test
  public void testSubtraction() throws Exception {
    Thread.sleep(3000);
    assert calculator.calculate(45, "-", 43) == 1;
  }

  @Test
  public void testMultiplication() throws Exception {
    Thread.sleep(3000);
    assert calculator.calculate(13, "*", 3) == 39;
  }

  @Test
  public void testDivision() throws Exception {
    Thread.sleep(3000);
    assert calculator.calculate(360, "/", 60) == 6;
  }

  public void testNotActuallyATest() {
    assert 1 == 0;
  }
}
