package example.Multithreading;

public class DeadlockExample {
    static class Account {
        private final String name;

        public Account(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    public static void transfer(Account from, Account to) {
        // Step1: Lock the 'from' account
        synchronized (from) {
            System.out.println(Thread.currentThread().getName() + " locked " + from.getName());

            try {
                // Simulate processing time to guarantee both threads grab their first lock
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println(Thread.currentThread().getName() + " waiting to lock " + to.getName());

            // Step 2: Try to lock the 'to' account while still holding the 'from' lock
            synchronized(to) {
                System.out.println(Thread.currentThread().getName() + " successfully transferred money.");
            }
        }
    }

    public static void main(String[] args) {
        Account accountA = new Account("Account-A");
        Account accountB = new Account("Account-B");

        // Thread 1: Locks Account A -> Wants Account B
        Thread t1 = new Thread(() -> transfer(accountA, accountB), "Thread-1");

        // Thread 2: locks Account B -> Wants Account A
        Thread t2 = new Thread(() -> transfer(accountB, accountA), "Thread-2");


        t1.start();
        t2.start();
    }
}


