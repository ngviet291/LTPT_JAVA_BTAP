/**
 * @ (#) DemoState.java   1.0     20/1/2026
 * <p>
 * Copyright (c) 2026 IUH. All rights reserved
 */
package state;


/**
 * @description
 * @author: Nguyen Tran Quoc Viet 
 * @version: 1.0
 * @created: 20/1/2026
 */

public class DemoState {
    public static void main(String[] args) {
        Object lock= new Object();
        Runnable task = ()->{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
       Runnable task2=()->{
           synchronized (lock){
                try {
                     Thread.sleep(1000);
                } catch (InterruptedException e) {
                     e.printStackTrace();
                }
           }
       };
        Runnable task3=()->{
            synchronized (lock){
                lock.notify();
            }
        };
         Thread thread1= new Thread (task);
         Thread thread2= new Thread (task2);
         Thread thread3= new Thread (task3);
         System.out.println("Thread 1 state: " + thread1.getState());
         System.out.println("Thread 2 state: " + thread2.getState());
         thread1.start();

         System.out.println("Thread 1 state after start: " + thread1.getState());
         
         thread2.start();
         System.out.println("Thread 2 state after start: " + thread2.getState());
    }
}
