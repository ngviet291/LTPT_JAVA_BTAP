package bai1;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class DemoBai1 {
    public static void main(String[] args) {
        Callable task1= new DisplayNum4();
        FutureTask ft1= new FutureTask<>(task1);
        Thread th1= new Thread(ft1);
        th1.start();

        Runnable task2= new CheckPrimeNum5(10);
        Thread th2= new Thread(task2);
        th2.start();
    }
}
