package bai3.normal;


public class Withdraws implements Runnable {
    private BankAccount bankAccount;

    public Withdraws(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            while (true) {

                bankAccount.get(100);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}
