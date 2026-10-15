public abstract class Account {

    // protected: visible to this class and its subclasses
    protected String accountNumber;
    protected double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Concrete method: same for every account type
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit rejected: amount must be positive.");
            return;
        }
        balance += amount;
        System.out.println("Deposited " + amount + " into " + accountNumber);
    }

    // Concrete method
    public double getBalance() {
        return balance;
    }

    // Abstract methods: no body here, every subclass MUST provide its own
    public abstract void withdraw(double amount);

    public abstract void endOfMonth();
}