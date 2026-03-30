package iuh.fit;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Callable<Long> call = new ComputationTask("Hello");
        FutureTask<Long> task = new FutureTask<>(call);
        Thread thread = new Thread(task);
        thread.start();
        long result = task.get();
        System.out.println("Ket qua la "+result);
    }
}