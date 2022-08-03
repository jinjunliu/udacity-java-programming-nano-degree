import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class TestRunner {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: TestRunner [test folder] [test name]");
            return;
        }
        List<String> passed = new ArrayList<>();
        List<String> failed = new ArrayList<>();
        Class<?> testClass = getTestClass(args[0], args[1]);
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.getAnnotation(Test.class) == null) {
                continue;
            }
            try {
                UnitTest test = (UnitTest) testClass.getConstructor().newInstance();
                test.beforeEachTest();
                method.invoke(test);
                test.afterEachTest();
                passed.add(getTestName(testClass, method));
            } catch (Throwable throwable) {
                failed.add(getTestName(testClass, method));
            }
        }

        System.out.println("Passed tests: " + passed);
        System.out.println("FAILED tests: " + failed);
    }

    private static Class<?> getTestClass(String testFolder, String className) throws Exception {
        URL testDir = Path.of(testFolder).toUri().toURL();
        URLClassLoader loader = new URLClassLoader(new URL[]{testDir});
        Class<?> klass = Class.forName(className, true, loader);
        if (!UnitTest.class.isAssignableFrom(klass)) {
            throw new IllegalArgumentException("Class " + klass.toString() + " must implement UnitTest");
        }
        return klass;
    }

    private static String getTestName(Class<?> klass, Method method) {
        return klass.getName() + "#" + method.getName();
    }
}
