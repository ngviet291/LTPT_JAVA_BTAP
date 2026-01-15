package bai2.section4;

public class Printer implements Runnable{
    private Storage storage;
    
    public Printer(Storage storage) {
        this.storage = storage;
        new Thread(this).start();
    }

    @Override
    public void run() {
       try {
        while (true) {
            int value= storage.get();
            System.out.println("print: " +value);
        }
       } catch (Exception e) {
        e.printStackTrace();
       }
    }
    
}
