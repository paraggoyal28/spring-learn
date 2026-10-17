package example.threads;

import java.util.Arrays;

public class Bank {
    private final double[] accounts;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    /*
        Transfers money from one account to another
        @param from the account to transfer from
        @param to the account to transfer to
        @param amount to be transfered
     */
    public void transfer(int from, int to, double amount) {
        if (accounts[from] < amount) return;
        System.out.println(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf("%10.2f from %d to %d", amount, from, to);
        accounts[to] += amount;
        System.out.printf("Total Balance: %10.2f\n", getTotalBalance());
    }

    /*
        Get the sum of all account balance
        @return the total balance

     */
    private double getTotalBalance() {
        double sum = 0;

        for (double a: accounts) {
            sum += a;
        }

        return sum;
    }

    public int size() {
        return accounts.length;
    }

}
