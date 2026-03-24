/**
 * @ (#) GarageLock.java   1.0     20/1/2026
 * <p>
 * Copyright (c) 2026 IUH. All rights reserved
 */
package packing2;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description
 * @author: Nguyen Tran Quoc Viet 
 * @version: 1.0
 * @created: 20/1/2026
 */

public class GarageLock {

    private final Lock lock= new ReentrantLock();
    private final Condition condition= lock.newCondition();

    private  int capacity;
    private int availableSlots;
    public GarageLock(int capacity) {
        this.capacity = capacity;
        this.availableSlots = capacity;
    }
    public void enter() throws InterruptedException {
        //lock không xử lý nghiệp vụ
        lock.lock();
        try {
            while(availableSlots==0){
                System.out.println("Lock: Garage is full, waiting for a slot...");
                condition.await();
            }
            System.out.println("Lock:Car is entering...");
            availableSlots--;
        }finally {
            lock.unlock();
        }
    }
    public void exit() throws InterruptedException {
        lock.lock();
        try {
            while(availableSlots==capacity){
                System.out.println("Lock:Garage is empty, waiting for a car to exit...");
                condition.await();
            }
            System.out.println("Lock: Car is exiting...");
            availableSlots++;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
    public int getAvailableSlots() {
        return availableSlots;
    }
}
