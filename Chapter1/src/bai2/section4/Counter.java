package bai2.section4;

public class Counter implements Runnable{
    private int value;
    private Storage storage;
    
    public Counter(int value, Storage storage) {
        this.value = value;
        this.storage = storage;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            int counter=0;
            while (counter<value) {
                System.out.println("Added :"+counter);
                storage.put(counter++);
                Thread.sleep(300);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
    }
    
}
