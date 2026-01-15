package bai1;

import java.util.concurrent.Callable;

public class DisplayNum4 implements Callable<Integer> {
    // private int num;
    // public DisplayNum(int num){
    //     this.num=num;
    // }
    @Override
    public Integer call() throws Exception {
        for (int i=1;i<=1000;i++){
            System.out.printf("%s\n",i);
        }
        return null;
    }
}
