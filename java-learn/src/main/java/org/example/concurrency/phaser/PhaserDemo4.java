package org.example.concurrency.phaser;

import java.util.concurrent.Phaser;

public class PhaserDemo4 {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(3) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("inside onAdvance() "+ this);
                return true;
            }
        };
        System.out.println("after constructor " + phaser);
        phaser.register();
        System.out.println("after register() " +  phaser);
        phaser.arrive();
        System.out.println("after arrive() " + phaser);
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("before arriveAndAwaitAdvance() " + phaser);
                phaser.arriveAndAwaitAdvance();
                System.out.println("after arriveAndAwaitAdvance() " + phaser);
            }
        };
        thread.start();
        phaser.arrive();
        System.out.println("after arrive() " + phaser);
        phaser.arriveAndDeregister();
        System.out.println("after arriveAndDeregister() " + phaser);
    }
}
