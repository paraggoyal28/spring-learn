package example.Multithreading;

// Using suspend() and resume()
class NewThread implements Runnable {
    String name; // Name of thread
    Thread t;

    NewThread(String threadName) {
        name = threadName;
        t = new Thread(this, name);
        System.out.println("New Thread: " + t);
        t.start(); // start the thread
    }

    // This is the entry point of the thread
    public void run() {
        try {
            for (int i = 15; i > 0; --i) {
                System.out.println(name + ": " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException exception) {
            System.out.println(name + " interrupted");
        }
        System.out.println(name + " exited");
    }
}

public class SuspendResume {
    public static void main(String[] args) {
        NewThread obj1 = new NewThread("One");
        NewThread obj2 = new NewThread("Two");

        try {
            Thread.sleep(1000);
           // obj1.t.suspend();
            System.out.println("Suspending thread one");
            Thread.sleep(1000);
            //obj1.t.resume();
            System.out.println("Resuming thread one");
           // obj2.t.suspend();
            System.out.println("Suspending thread two");
            Thread.sleep(1000);
           // obj2.t.resume();
            System.out.println("Resuming thread two");
        } catch (InterruptedException e) {
            System.out.println("Main Thread interrupted");
        }

        // Wait for threads to finish
        try {
            System.out.println("Waiting for threads to finish");
            obj1.t.join();
            obj2.t.join();
        } catch (InterruptedException e) {
            System.out.println("Main Thread Interrupted..");
        }
        System.out.println("Main thread exited...");
    }
}
