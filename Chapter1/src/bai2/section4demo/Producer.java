package bai2.section4demo;

public class Producer implements Runnable{
    private MyQueue q;
    
    public Producer(MyQueue q) {
        this.q = q;
        new Thread(this).start();
    }

    @Override
    public void run(){
        int i=0;
        while (true) {
            try { 
                q.put(i++);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

}
