public class CurrentAccount extends Account {

    private static final double MONTHLY_FEE = 10.0;   // fixed maintenance fee
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, double balance, double overdraftLimit) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal rejected: amount must be positive.");
            return;
        }
        // The balance may go negative, but not below -overdraftLimit
        if (balance - amount < -overdraftLimit) {
            System.out.printf("Withdrawal of %.2f rejected on %s: would exceed the overdraft limit of %.2f.%n",
                    amount, accountNumber, overdraftLimit);
            return;
        }
        balance -= amount;
        if (balance < 0) {
            System.out.printf("Withdrew %.2f from %s. New balance: %.2f (in overdraft)%n",
                    amount, accountNumber, balance);
        } else {
            System.out.printf("Withdrew %.2f from %s. New balance: %.2f%n",
                    amount, accountNumber, balance);
        }
    }

    @Override
    public void endOfMonth() {
        if (balance - MONTHLY_FEE < -overdraftLimit) {
            System.out.printf("Maintenance fee of %.2f rejected on %s: would exceed the overdraft limit of %.2f.%n",
                    MONTHLY_FEE, accountNumber, overdraftLimit);
            return;
        }
        balance -= MONTHLY_FEE;
        System.out.printf("Maintenance fee of %.2f deducted from %s. New balance: %.2f%n",
                MONTHLY_FEE, accountNumber, balance);
    }

    public static void main(String[] args) {
        CurrentAccount c = new CurrentAccount("CA-001", 200, 500);
        c.withdraw(600);     // balance goes to -400, within the 500 limit
        c.withdraw(200);     // would reach -600, past the limit
        c.endOfMonth();
    }
}