package example.Multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;

public class ExecutorServiceDemo {
    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            Callable<String> task = () -> {
                Thread.sleep(1000);
                return "Data from server";
            };

            Future<String> future = executorService.submit(task);

            // This blocks the main thread until the computation is ready
            String result = future.get();
            System.out.println(result);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
