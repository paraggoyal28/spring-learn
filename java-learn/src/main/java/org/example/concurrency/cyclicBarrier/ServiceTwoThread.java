package org.example.concurrency.cyclicBarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ServiceTwoThread implements Runnable {
    List<List<Integer>> partialResults;
    CyclicBarrier cyclicBarrier;

    public ServiceTwoThread(List<List<Integer>> partialResults, CyclicBarrier cyclicBarrier) {
        this.partialResults = partialResults;
        this.cyclicBarrier = cyclicBarrier;
    }


    @Override
    public void run() {
        Random random = new Random();
        System.out.println("Service-2 run method");
        List<Integer> partialResult = new ArrayList<>();
        for(int i = 0; i < 10; ++i) {
            Integer num = random.nextInt(5);
            partialResult.add(num);
        }
        partialResults.add(partialResult);

        try {
            System.out.println("Service-2 numbers: " + partialResult);
            System.out.println("Service-2 waiting for others to reach barrier.");
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException ignored) {

        }
    }
}
