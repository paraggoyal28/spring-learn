package example.Multithreading;

class NewThread implements Runnable {
    String name; // name of thread
    Thread t;
    boolean suspendFlag;

    NewThread(String threadName) {
        this.name = threadName;
        t = new Thread(this, name);
        System.out.println("New Thread: " + t);
        suspendFlag = false;
        t.start();
    }

    public void run() {
        try {
            for (int i = 15; i > 0; --i) {
                System.out.println(name + ": "  + i);
                Thread.sleep(200);
                synchronized (this) {
                    while (suspendFlag) {
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println(name + " interrupted");
        }

        System.out.println(name + " exited");
    }

    void mySuspend() {
        suspendFlag = true;
    }

    synchronized void myResume() {
        suspendFlag = false;
        notify();
    }
}

public class SuspendResumeNew {
    public static void main(String[] args) {
        NewThread obj1 = new NewThread("One");
        NewThread obj2 = new NewThread("Two");

        try {
            Thread.sleep(1000);
            obj1.mySuspend();
            System.out.println("Suspending Thread One");
            Thread.sleep(1000);
            obj1.myResume();
            System.out.println("Resuming Thread One");
            obj2.mySuspend();
            System.out.println("Suspending Thread Two");
            Thread.sleep(1000);
            obj2.myResume();
            System.out.println("Resuming Thread Two");
        }  catch (InterruptedException e) {
            System.out.println("Main Thread Interrupted");
        }

        // wait for threads to finish
        try {
            System.out.println("Waiting for threads to finish.");
            obj1.t.join();
            obj2.t.join();
        } catch (InterruptedException e) {
            System.out.println("Main Thread interrupted");
        }
        System.out.println("Main Thread exited");
    }
}

