package com.ngviet291;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
        static Counter counter = new Counter();
//NguyenTranQuocViet-23660721
    public static void main(String[] args) {
        Runnable r= ()->{
            counter.increment();
        };
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executor.submit(r);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {;
        }
        System.out.println("Final count is: " + counter.getCount());
    }
}