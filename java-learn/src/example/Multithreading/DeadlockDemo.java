package example.Multithreading;

import java.sql.ResultSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

class Resource {
    public  synchronized void test1(Resource resource) {
        System.out.println("Inside test1 method " + Thread.currentThread().getName());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test2(resource);
    }
    public static synchronized void test2(Resource resource) {
        System.out.println("Inside Test2 Method " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        resource.test1(resource);
    }
}

class Task1 implements Runnable {
    private Resource resource;

    public Task1(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        resource.test1(resource);
       // resource.test2();
        System.out.println("Invoked Test2 Method from Task1");
    }
}

class Task2 implements Runnable {
    private Resource resource;

    public Task2(Resource resource) { this.resource = resource; }

    @Override
    public void run() {
        resource.test2(resource);
      //  resource.test1();
        System.out.println("Invoked Test1 Method from Task2");
    }
}

public class DeadlockDemo {
    public static void main(String[] args) {

        Resource resource =  new Resource();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Thread t1 = new Thread(new Task1(resource));
        Thread t2 = new Thread(new Task2(resource));
        t1.start();
        t2.start();

    }
}
