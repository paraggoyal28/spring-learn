package org.example.concurrency.cyclicBarrier2;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static java.lang.Thread.sleep;

public class CyclicBarrierDemo {
    private static final int PARTIES = 3;
    private static final int ITERATIONS = 3;

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier entryBarrier = new CyclicBarrier(PARTIES + 1, () ->
            System.out.println("iteration started")
        );
        CyclicBarrier exitBarrier = new CyclicBarrier(PARTIES + 1, () ->
                System.out.println("iteration finished")
        );

        for(int i = 0; i < ITERATIONS; ++i) {
            for(int p = 0; p < PARTIES; ++p) {
                int delay = p + 1;
                Runnable task = new Worker(delay, entryBarrier, exitBarrier);
                new Thread(task).start();
            }
            System.out.println("all threads waiting to start: iteration " + i);
            Thread.sleep(1);
            entryBarrier.await();
            System.out.println("all threads started: iteration " + i);
            exitBarrier.await();
            System.out.println("all threads finished: iteration " + i);
        }
    }

    private static class Worker implements Runnable {
        private final int delay;
        private final CyclicBarrier entryBarrier;
        private final CyclicBarrier exitBarrier;
        public Worker(int delay, CyclicBarrier entryBarrier, CyclicBarrier exitBarrier) {
            this.delay = delay;
            this.entryBarrier = entryBarrier;
            this.exitBarrier = exitBarrier;
        }

        @Override
        public void run() {
            try {
                entryBarrier.await();
                work();
                exitBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }

        private void work() throws InterruptedException {
            System.out.println("work " + delay + " started");
            Thread.sleep(delay);
            System.out.println("work " + delay + " finished");
        }
    }
}
