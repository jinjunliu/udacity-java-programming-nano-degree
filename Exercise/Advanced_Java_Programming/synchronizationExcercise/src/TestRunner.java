import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class TestRunner {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: TestRunner [test folder] [test name]");
            return;
        }
        Instant programStart = Instant.now();

        List<Method> testMethods = new ArrayList<>();
        Class<?> testClass = getTestClass(args[0], args[1]);
        for (Method method : testClass.getDeclaredMethods()) {
            Test annotation = method.getAnnotation(Test.class);
            if (annotation != null) {
                testMethods.add(method);
            }
        }
        // You should not have to change any code above this line.

        // TODO: Use locks or another synchronization mechanism to guard these lists and
        Object passedLock = new Object();
        List<String> passed = new ArrayList<>();
        Object failedLock = new Object();
        List<String> failed = new ArrayList<>();

        // TODO: Create a CountDownLatch to join the test threads at the end. There are other ways to
        //       wait for the threads to finish, but CountDownLatch is the easiest way in this case.
        //       Make sure you initialize it using the correct count!
        CountDownLatch latch = new CountDownLatch(testMethods.size());
        // TODO: Create an ExecutorService using Executors.newFixedThreadPool(N), where N is the number
        //       of threads in the thread pool.
        ExecutorService executor = Executors.newFixedThreadPool(testMethods.size());

        for (Method method : testMethods) {

            // TODO: Move this code to inside of a Runnable object, and pass it to the ExecutorService.
            //       You can do this with lambdas:
            //           executor.execute(() -> {
            //             // Run the test code here.
            //           });
            executor.execute(() -> {
                try {
                    UnitTest test = (UnitTest) testClass.getConstructor().newInstance();
                    test.beforeEachTest();
                    method.invoke(test);
                    test.afterEachTest();
                    synchronized (passedLock) {
                        passed.add(getTestName(testClass, method));
                    }
                } catch (Throwable throwable) {
                    synchronized (failedLock) {
                        failed.add(getTestName(testClass, method));
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        // Shut down the executor service (ExecutorService#shutdown()), and then wait for all the
        // threads to finish by calling CountDownLatch#await().
        executor.shutdown();
        latch.await();

        Duration executionTime = Duration.between(programStart, Instant.now());

        // This code should remain unchanged.
        System.out.println("Passed tests: " + passed);
        System.out.println("FAILED tests: " + failed);
        System.out.println("Test execution took " + executionTime.toSeconds() + " second(s).");
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
