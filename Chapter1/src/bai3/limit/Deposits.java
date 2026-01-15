package limit;

public class Deposits implements Runnable {
    private BankAccount bankAccount;

    public Deposits(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                bankAccount.put(100);

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}
