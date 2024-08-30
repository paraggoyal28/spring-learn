package org.example.concurrency.countDownLatch;


import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

public class CountDownLatchDemo {
    private static final int PARTIES = 3;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch entryBarrier = new CountDownLatch(1);
        CountDownLatch exitBarrier = new CountDownLatch(PARTIES);

            for (int p = 0; p < PARTIES; ++p) {
                int delay = p + 1;
                Runnable task = new Worker(delay, entryBarrier, exitBarrier);
                new Thread(task).start();
            }


            System.out.println("all threads waiting to start");
            Thread.sleep(1);

            entryBarrier.countDown();
            System.out.println("all threads started");

            exitBarrier.await();
            System.out.println("all threads finished");

    }

    private static class Worker implements Runnable {

        private final int delay;
        private final CountDownLatch entryBarrier;
        private final CountDownLatch exitBarrier;

        public Worker(int delay, CountDownLatch entryBarrier, CountDownLatch exitBarrier) {
            this.delay = delay;
            this.entryBarrier = entryBarrier;
            this.exitBarrier = exitBarrier;
        }

        @Override
        public void run() {
            try {
                entryBarrier.await();
                work();
                exitBarrier.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        private void work() throws InterruptedException {
            System.out.println("Work " + delay + " started");
            Thread.sleep(delay);
            System.out.println("Work " + delay + " finished");
        }
    }
}
