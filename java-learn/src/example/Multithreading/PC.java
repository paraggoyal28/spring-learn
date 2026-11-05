package example.Multithreading;


class Queue {
    int n;

    synchronized int get() {
        System.out.println("Get: " + n);
        return n;
    }

    synchronized void put(int n) {
        this.n = n;
        System.out.println("Put: " + n);
    }
}
class ProducerWithQ implements Runnable {
    Queue q;

    ProducerWithQ(Queue q) {
        this.q = q;
        new Thread(this, "Producer").start();
    }

    public void run() {

        int i = 0;
        while (true) {
            q.put(i++);
        }
    }
}

class ConsumerWithQ implements Runnable {
    Queue q;

    ConsumerWithQ(Queue q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }

    public void run() {
        while (true) {
            q.get();
        }
    }
}


class PC  {
    public static void main(String[] args) {
        Queue q  = new Queue();

        new ProducerWithQ(q);
        new ConsumerWithQ(q);
        System.out.println("Press Control + C to stop\n");
    }
}