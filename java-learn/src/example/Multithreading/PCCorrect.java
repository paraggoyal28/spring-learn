package example.Multithreading;

class Q {
    int n;
    boolean valueSet = false;

    synchronized int get() {
        while (!valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Consumer Interrupted");
            }
        }

        System.out.println("Received n: " + this.n);
        valueSet = false; // Allow producer to run
        notify();
        return n;
    }

    synchronized void put(int n) {
        while (valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Producer Interrupted");
            }
        }

        this.n = n;
        System.out.println("Producer n: " + this.n);
        valueSet = true; // Allow producer to run
        notify();
    }
}

class Producer implements Runnable {
    Q q;
    Producer(Q q) {
        this.q = q;
        new Thread(this, "Producer").start();
    }

    public void run() {
        int i = 0;
        while (true) {
            q.put(i++);
            if (i == 6) {
                break;
            }
        }
    }
}

class Consumer implements Runnable {
    Q q;
    Consumer(Q  q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }

    public void run() {
        while (true) {
            q.get();
        }
    }
}


public class PCCorrect {
    public static void main(String[] args) {
        Q q  = new Q();
        new Producer(q);
        new Consumer(q);
    }
}
