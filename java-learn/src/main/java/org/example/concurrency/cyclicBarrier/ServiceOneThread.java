package org.example.concurrency.cyclicBarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ServiceOneThread implements Runnable {
    List<List<Integer>> partialResults;
    CyclicBarrier cyclicBarrier;

    public ServiceOneThread(List<List<Integer>> partialResults, CyclicBarrier cyclicBarrier) {
        this.partialResults = partialResults;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        Random random = new Random();
        System.out.println("Service-1 run method");
        List<Integer> partialResult = new ArrayList<>();
        for(int i = 0; i < 10; ++i) {
            Integer num = random.nextInt(10);
            partialResult.add(num);
        }
        System.out.println("Service-1 partial results: " + partialResult);
        partialResults.add(partialResult);

        try {
            System.out.println("Service-1 is moving to sleeping state...");
            Thread.sleep(1000);
            System.out.println("Service-1 is coming from sleeping state....");
            System.out.println("Service-1 waiting for others to reach barrier.");
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException ignored) {

        }
    }
}
