package bai1;

public class CheckPrimeNum5 implements Runnable{
    private int num;
    public CheckPrimeNum5(int num){
        this.num=num;
    }
    @Override
    public void run() {
        if(num<2){
            System.out.printf("%s : Kh phai snt",num);
            return;
        }
        int squareRoot = (int) Math.sqrt(num);
        int i;
        for (i = 2; i <= squareRoot; i++) {
            if (num % i == 0) {
                System.out.printf("%s : Kh phai snt",num);
                return;
            }
        }
        System.out.printf("%s : La snt",num);
    }
    
}
