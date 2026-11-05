package example.Multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ResourceRe {
    private final Lock instanceLock = new ReentrantLock();
    private static final Lock classLock = new ReentrantLock();

    public void test1() {
        // Enforce global order, Class lock first, and then instance lock
        classLock.lock();
        try {
            Thread.sleep(1000);
            instanceLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " executed test1");
            } finally {
                instanceLock.unlock();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            classLock.unlock();
        }
    }

    public void test2() {
        // Enforce the exact same global order; Class lock first, and then Instance Lock
        classLock.lock();
        try {
            Thread.sleep(1000);
            instanceLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " executed test2");
            } finally {
                instanceLock.unlock();
            }
        } catch (InterruptedException e) {
           Thread.currentThread().interrupt();
        } finally {
            classLock.unlock();
        }
    }
}

class Task1Res implements  Runnable {
    private ResourceRe resource;
    public Task1Res(ResourceRe resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        resource.test1();
        resource.test2();
    }
}

class Task2Res implements Runnable {
    private ResourceRe resource;
    public Task2Res(ResourceRe resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        resource.test2();
        resource.test1();
    }
}


public class LockOrderingDemo {
    public static void main(String[] args) {
        ResourceRe resource = new ResourceRe();
        Thread t1 = new Thread(new Task1Res(resource), "Thread-1");
        Thread t2 = new Thread(new Task2Res(resource), "Thread-2");
        t1.start();
        t2.start();
    }
}
