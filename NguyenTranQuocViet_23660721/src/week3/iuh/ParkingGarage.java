/**
 * @ (#) ParkingGarage.java   1.0     20/1/2026
 * <p>
 * Copyright (c) 2026 IUH. All rights reserved
 */
package week3.iuh;


/**
 * @description
 * @author: Nguyen Tran Quoc Viet 
 * @version: 1.0
 * @created: 20/1/2026
 */

public class ParkingGarage {
    private  int capacity;
    private int availableSlots;
    public ParkingGarage(int capacity){
        this.capacity=capacity;
        this.availableSlots=capacity;
    }
    public  synchronized void enter() throws InterruptedException{
        Thread.sleep(500);

        while(availableSlots==0){
            System.out.println("Garage is full, waiting for a slot...");
            wait();
        }
            System.out.println("Car is entering...");
            availableSlots--;

    }
    public synchronized void exit() throws InterruptedException{
        Thread.sleep(500);
        if(availableSlots<capacity){
            System.out.println("Car is exiting...");
            availableSlots++;
            notifyAll();
        }
    }
    public int getAvailableSlots(){
            return availableSlots;
    }
}
