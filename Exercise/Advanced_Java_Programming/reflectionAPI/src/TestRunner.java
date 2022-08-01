import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class TestRunner {

  private static final List<Class<?>> TESTS = List.of(CalculatorTest.class);

  public static void main(String[] args) throws Exception {

    List<String> passed = new ArrayList<>();
    List<String> failed = new ArrayList<>();

    for (Class<?> klass : TESTS) {
      if (!UnitTest.class.isAssignableFrom(klass)) {
        throw new IllegalArgumentException("Class " + klass.toString() + " must implement UnitTest");
      }

      for (Method method : klass.getDeclaredMethods()) {
        if (method.getAnnotation(Test.class) != null) {
          try {
            UnitTest test = (UnitTest) klass.getConstructor().newInstance();
            test.beforeEachTest();
            method.invoke(test);
            test.afterEachTest();
            passed.add(getTestName(klass, method));
          } catch (Throwable throwable) {
            failed.add(getTestName(klass, method));
          }
        }
      }
    }

    System.out.println("Passed tests: " + passed);
    System.out.println("FAILED tests: " + failed);
  }

  private static String getTestName(Class<?> klass, Method method) {
    return klass.getName() + "#" + method.getName();
  }
}
