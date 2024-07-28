package org.example.concurrency.cyclicBarrier;

import java.util.List;

public class AggregatorThread implements Runnable {
    List<List<Integer>> partialResults;

    public AggregatorThread(List<List<Integer>> partialResults) {
        this.partialResults = partialResults;
    }

    @Override
    public void run() {
        String thisThreadName = Thread.currentThread().getName();
        System.out.println(thisThreadName + " : Computing sum of 2 services");
        int sum = 0;
        System.out.println(partialResults);
        for(List<Integer> threadResult: partialResults) {
            System.out.print("Adding ");
            for(Integer partialResult: threadResult) {
                System.out.print(partialResult + " ");
                sum += partialResult;
            }
            System.out.println();
        }
        System.out.println(thisThreadName + " : Final result = " + sum);
    }
}
