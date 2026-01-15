package bai2.section4;

public class Storage {
    private int value;
    private boolean available=false;
    public synchronized void put (int value) throws InterruptedException{
        while (available) {
            wait();
        }

        available = true;
        this.value= value;
        notifyAll();
    }
    public synchronized int get() throws InterruptedException{
        while (!available) {
            wait();
        }
        available=false;
        notifyAll();
        return value;
    }
}
