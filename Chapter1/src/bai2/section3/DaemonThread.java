package bai2.section3;

public class DaemonThread extends Thread{
    public void run(){
        System.out.println("Entering run  method");
        try{
            System.out.println("In run Method: currentThread() is "+ Thread.currentThread());
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }finally{
            System.out.println("Leaving run Method");
        }
    }
    public static void main(String[] args) {

        System.out.println("Entering main Method");
        DaemonThread t= new DaemonThread();

        // t.setDaemon(true);

        t.start();
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("Leaving main method");

    }
}
