package bai2.section4demo;

public class MyQueue {
    private int n;
    private boolean valueSet= false;
    public synchronized int get() throws InterruptedException{
        while (!valueSet) {
            wait();
        }
        System.out.println("Got: " +n);
        notifyAll();
        valueSet=false;
        return n;
    }
    public synchronized void put (int n) throws InterruptedException{
        while (valueSet) {
            wait();
        }
        this.n=n;
        valueSet=true;
        System.out.println("Put: " +n);
        notifyAll();
    }
}
