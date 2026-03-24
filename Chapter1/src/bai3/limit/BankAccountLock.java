package bai3.limit;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccountLock {
    private double balance;
    private static final double MAX_BALANCE = 100000;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public BankAccountLock(double balance) {
        this.balance = balance;
    }

    public void put(double deposits) {
        lock.lock();
        try {
            while (balance + deposits> MAX_BALANCE) {
                condition.await();
            }
            balance += deposits;
            System.out.println("Nap vao 100 do: " + balance);
            condition.signalAll();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void get(double withdraws) {
        lock.lock();
        try {
            while (balance < withdraws) {
                condition.await();
            }
            balance -= withdraws;
            System.out.println("Rut ra 100 do: " + balance);
            condition.signalAll();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
