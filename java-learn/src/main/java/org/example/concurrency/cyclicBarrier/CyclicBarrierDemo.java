package org.example.concurrency.cyclicBarrier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    private CyclicBarrier cyclicBarrier;

    private List<List<Integer>> partialResults = Collections.synchronizedList(new ArrayList<>());


    public void runSimulation() {
        cyclicBarrier = new CyclicBarrier(2, new AggregatorThread(partialResults));

        Thread worker1 = new Thread(new ServiceOneThread(partialResults, cyclicBarrier));

        worker1.start();

        Thread worker2 = new Thread(new ServiceTwoThread(partialResults, cyclicBarrier));

        worker2.start();
    }

    public static void main(String[] args) {
        CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo();
        cyclicBarrierDemo.runSimulation();
    }
}
