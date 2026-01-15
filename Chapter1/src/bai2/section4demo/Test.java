package bai2.section4demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
        System.out.println("Press Control-C to stop");
        ExecutorService exs= Executors.newFixedThreadPool(2);
        MyQueue q= new MyQueue();
        exs.execute(new Producer(q));
        exs.execute(new Consumer(q));
        exs.shutdown();
    }
}
