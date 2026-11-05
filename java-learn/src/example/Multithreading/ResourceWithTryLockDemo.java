package example.Multithreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ResourceWithTryLock {
    private final Lock instanceLock = new ReentrantLock();
    private static final Lock classLock = new ReentrantLock();

    public boolean executeWithTryLock(boolean callTest1First) {
        String threadName = Thread.currentThread().getName();

        try {
            // Attempt to acquire locks with a timeout to prevent infinite blocking
            if (instanceLock.tryLock(100, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println(threadName + " acquired instance lock");

                    Thread.sleep(1000);

                    if (classLock.tryLock(100, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println(threadName + " acquired class lock. Success!");
                            return true;
                        } finally {
                            classLock.unlock();
                        }
                    }
                } finally {
                    instanceLock.unlock();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(threadName + " failed to acquire lock. Backing off..");
        return false;
    }
}

class Task1ResourceTryLock implements Runnable {
    ResourceWithTryLock resourceWithTryLock;
    public Task1ResourceTryLock(ResourceWithTryLock resourceWithTryLock) {
        this.resourceWithTryLock = resourceWithTryLock;
    }

    @Override
    public void run() {
        this.resourceWithTryLock.executeWithTryLock(true);
    }
}

class Task2ResourceTryLock implements Runnable {
    ResourceWithTryLock resourceWithTryLock;
    public Task2ResourceTryLock(ResourceWithTryLock resourceWithTryLock) {
        this.resourceWithTryLock = resourceWithTryLock;
    }

    @Override
    public void run() {
        this.resourceWithTryLock.executeWithTryLock(true);
    }
}

public class ResourceWithTryLockDemo {
    public static void main(String[] args) {
        ResourceWithTryLock resourceWithTryLock = new ResourceWithTryLock();
        Thread t1 = new Thread(new Task1ResourceTryLock(resourceWithTryLock), "Thread-1");
        Thread t2 = new Thread(new Task2ResourceTryLock(resourceWithTryLock), "Thread-2");
        t1.start();
        t2.start();
    }
}

