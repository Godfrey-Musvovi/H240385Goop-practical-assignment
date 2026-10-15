import java.util.ArrayList;
import java.util.List;

public class BankDemo {
    public static void main(String[] args) {

        // One list, declared with the PARENT type, holding both kinds of account
        List<Account> accounts = new ArrayList<>();
        accounts.add(new SavingsAccount("SA-001", 500));
        accounts.add(new SavingsAccount("SA-002", 150));
        accounts.add(new CurrentAccount("CA-001", 200, 500));
        accounts.add(new CurrentAccount("CA-002", 1000, 300));

        System.out.println("=== Withdrawals (250.00 from each account) ===");
        for (Account acc : accounts) {
            acc.withdraw(250);        // no casting: Java runs each object's own version
        }

        System.out.println();
        System.out.println("=== End of month ===");
        for (Account acc : accounts) {
            acc.endOfMonth();         // interest for savings, fee for current
        }

        System.out.println();
        System.out.println("=== Final balances ===");
        for (Account acc : accounts) {
            System.out.printf("%s -> %.2f%n", acc.getClass().getSimpleName(), acc.getBalance());
        }
    }
}