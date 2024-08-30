package org.example.concurrency.phaser;

import java.util.concurrent.Phaser;

import static java.lang.Thread.sleep;

public class PhaserDemo2 {
    private static final int PARTIES = 3;

    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(1);
        System.out.println("After construction " + phaser);
        for(int p = 0; p < PARTIES; ++p) {
            int delay = p + 1;
            Runnable task = new Worker(delay, phaser);
            new Thread(task).start();
        }

        System.out.println("all threads waiting to start " + phaser);
        Thread.sleep(1);

        System.out.println("before all threads started " + phaser);
        phaser.arriveAndDeregister();
        System.out.println("after all threads started " + phaser);

        phaser.register();
        while(!phaser.isTerminated()) {
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndDeregister();
        }

        System.out.println("all threads finished  " + phaser);
    }

    private static class Worker implements Runnable {
        private final int delay;
        private final Phaser phaser;

        public Worker(int delay, Phaser phaser) {
            phaser.register();
            this.delay = delay;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            phaser.arriveAndAwaitAdvance();
            try {
                work();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            phaser.arriveAndDeregister();
        }

        private void work() throws InterruptedException {
            System.out.println("work " + delay + " started");
            Thread.sleep(delay);
            System.out.println("work " + delay + " finished");
        }
    }
}
