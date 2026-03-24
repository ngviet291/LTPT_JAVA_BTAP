/**
 * @ (#) DemoGarage.java   1.0     20/1/2026
 * <p>
 * Copyright (c) 2026 IUH. All rights reserved
 */
package week3.iuh;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description
 * @author: Nguyen Tran Quoc Viet 
 * @version: 1.0
 * @created: 20/1/2026
 */

public class DemoGarage {
    static ParkingGarage garage = new ParkingGarage(10);

    public static void main(String[] args) {
        Runnable enterTask = () -> {
            try {
                garage.enter();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable exitTask = () -> {
            try {
                garage.exit();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        ;
        ExecutorService exs = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            exs.submit(enterTask);
        }
        exs.submit(enterTask);
        exs.submit(enterTask);
        exs.submit(enterTask);
        exs.submit(enterTask);
        exs.submit(enterTask);

        exs.submit(exitTask);

        exs.shutdown();
        while (!exs.isTerminated()) {}
        System.out.println("Available slots after entering: " + garage.getAvailableSlots());
    }
}
