package example.Multithreading;

class NewThread implements Runnable {
    Thread t;

    NewThread() {
        // create a new second thread
        t = new Thread(this, "Demo Thread");
        System.out.println("Child Thread: " + t);
        t.start(); // Start the thread
    }

    // This is the entry point for the second thread
    public void run() {
        try {
            for (int i = 5; i > 0; --i) {
                System.out.println("Child Thread: " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Child interrupted");
        }
        System.out.println("Exiting Child Thread");
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        new NewThread(); // create a new thread

        try {
            for (int i = 0; i < 5; ++i) {
                System.out.println("Main Thread: ");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Main Thread Interrupted");
        }
        System.out.println("Main Thread exited");
    }
}
