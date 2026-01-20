package bai3.normal;
public class BankAccount {
    private double balance;
    // private static final double MAX_VALUE=100000;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public synchronized void put(double deposits) throws InterruptedException {
        balance = deposits + balance;
        System.out.println("Nop vao bankaccount 100: " + balance);
        notifyAll();
    }

    public synchronized void get(double withdraws) throws InterruptedException {
        balance = balance - withdraws;
        while (balance<withdraws) {
            wait();
        }
        System.out.println("Rut ra bankaccount 100: " + balance);
        Thread.sleep(1000);

        notifyAll();
    }
}
