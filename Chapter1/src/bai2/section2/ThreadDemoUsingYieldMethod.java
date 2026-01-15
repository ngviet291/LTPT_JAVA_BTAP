package bai2.section2;

public class ThreadDemoUsingYieldMethod implements Runnable{
    private Thread t;
    public ThreadDemoUsingYieldMethod(String str){
        t= new Thread(this, str);
        t.start();
    }
    @Override
    public void run() {
        for(int i=0;i<5;i++){
            if((i%5)==0){
                System.out.println(Thread.currentThread().getName()+"yielding control...");
                Thread.yield();
                 try {
                Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }
        System.out.println(Thread.currentThread().getName()+"has finished");

    }
    public static void main(String[] args) {
        new ThreadDemoUsingYieldMethod("Thread1");
        new ThreadDemoUsingYieldMethod("Thread2");
        new ThreadDemoUsingYieldMethod("Thread3");
    }

}
