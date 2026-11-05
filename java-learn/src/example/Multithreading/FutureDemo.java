package example.Multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        System.out.println("Main: Submitting order fetching task: ");

        // Submit a task that returns a result
        Future<String> futureOrder = executor.submit(() -> {
            Thread.sleep(1000);
            System.out.println("First Task: "  + Thread.currentThread().getName());
            return "Order #12345";
        });

        executor.submit(() -> {
            System.out.println("Hello World...");
            System.out.println("Second Task: " + Thread.currentThread().getName());
            System.out.println("Second Task Finished " + System.currentTimeMillis() + " " +
                        Thread.currentThread().getName());
        });

        System.out.println("Main: Doing other work while waiting...");

        // Blocking line: Main thread stops here until the feature completes
        String result = futureOrder.get();
        System.out.println("First Task Finished " + System.currentTimeMillis() + " " +
                Thread.currentThread().getName());

        System.out.println("Main: Received result: " + result);
        executor.shutdown();
    }
}
