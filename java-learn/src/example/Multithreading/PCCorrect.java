package example.Multithreading;

class QueuePC {
    int n;
    boolean valueSet = false;

    synchronized int get() {
        while (!valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("ConsumerWithQueuePC Interrupted");
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

class ProducerWithQueuePC implements Runnable {
    QueuePC QueuePC;
    ProducerWithQueuePC(QueuePC QueuePC) {
        this.QueuePC = QueuePC;
        new Thread(this, "Producer").start();
    }

    public void run() {
        int i = 0;
        while (true) {
            QueuePC.put(i++);
            if (i == 6) {
                break;
            }
        }
    }
}

class ConsumerWithQueuePC implements Runnable {
    QueuePC QueuePC;
    ConsumerWithQueuePC(QueuePC  QueuePC) {
        this.QueuePC = QueuePC;
        new Thread(this, "ConsumerWithQueuePC").start();
    }

    public void run() {
        while (true) {
            QueuePC.get();
        }
    }
}


public class PCCorrect {
    public static void main(String[] args) {
        QueuePC QueuePC  = new QueuePC();
        new ProducerWithQueuePC(QueuePC);
        new ConsumerWithQueuePC(QueuePC);
    }
}
