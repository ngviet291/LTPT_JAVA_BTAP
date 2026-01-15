package bai2.section1;

public class YourTask implements Runnable {

    @Override
    public void run() {
        try {
            Thread t = new Thread(new AnotherTask("Another Task", 10));
            t.start();
            for(int i=0;i<8;i++){
                System.out.println("Your Task #"+i);
                if(i==2){
                    t.join();
                }
            }
        } catch (InterruptedException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}
