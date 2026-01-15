package bai2.section4demo;

public class Consumer implements Runnable {
    private MyQueue q;
    
    public Consumer(MyQueue q) {
        this.q = q;
        new Thread(this).start();
    }

    @Override
    public void run() {
       while (true) {
            try {
                q.get();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
       }
    }
    
}
