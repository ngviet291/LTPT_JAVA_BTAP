package bai3.normal;

public class Main {
    public static void main(String[] args) {
        // BankAccount bankAccount = new BankAccount(0);
        // Deposits deposits = new Deposits(bankAccount);
        // Withdraws withdraws = new Withdraws(bankAccount);
        BankAccountLock bankAccountLock = new BankAccountLock(0);
        Thread depositsThread = new Thread(() -> {
            try {
                while (true) {
                    bankAccountLock.put(100);

                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        });
        Thread withdrawsThread = new Thread(() -> {
            try {
                while (true) {

                    bankAccountLock.get(100);
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        });
        withdrawsThread.start();
        depositsThread.start();
    }
}
