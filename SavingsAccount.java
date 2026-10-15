public class SavingsAccount extends Account {

    // Fixed values, the same for every savings account
    private static final double MINIMUM_BALANCE = 100.0;
    private static final double INTEREST_RATE = 0.02;   // 2% per month

    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);   // passes the values up to Account's constructor
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal rejected: amount must be positive.");
            return;
        }
        if (balance - amount < MINIMUM_BALANCE) {
            System.out.printf("Withdrawal of %.2f rejected on %s: balance cannot fall below the minimum of %.2f.%n",
                    amount, accountNumber, MINIMUM_BALANCE);
            return;
        }
        balance -= amount;
        System.out.printf("Withdrew %.2f from %s. New balance: %.2f%n", amount, accountNumber, balance);
    }

    @Override
    public void endOfMonth() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        System.out.printf("Interest of %.2f added to %s. New balance: %.2f%n", interest, accountNumber, balance);
    }
}