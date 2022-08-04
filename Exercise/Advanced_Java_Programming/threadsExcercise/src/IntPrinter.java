import java.util.ArrayList;
import java.util.List;

public final class IntPrinter {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: Main <number of threads>");
            return;
        }

        int n = Integer.parseInt(args[0]);

        List<Thread> threads = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int finalI = i;
            threads.add(new Thread(new IntRunner(finalI)));
        }
        for (Thread t: threads) {
            t.start();
        }
        for (Thread t: threads) {
            t.join();
        }
    }

    private record IntRunner(int value) implements Runnable {

        @Override
            public void run() {
                System.out.println("Thread #" + Thread.currentThread().getId() + " printed " + value);
            }
        }

}
