import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<String, Account> accounts = new HashMap<>();
    private int counter = 1001;

    public Account createSavingsAccount(String name, double openingBalance) {
        if (openingBalance < 500) {
            throw new InvalidAmountException("Savings account needs at least 500 to open");
        }
        String number = "ACC" + counter++;
        Account account = new SavingsAccount(number, name, openingBalance);
        accounts.put(number, account);
        return account;
    }

    public Account createCurrentAccount(String name, double openingBalance) {
        if (openingBalance < 0) {
            throw new InvalidAmountException("Opening balance cannot be negative");
        }
        String number = "ACC" + counter++;
        Account account = new CurrentAccount(number, name, openingBalance);
        accounts.put(number, account);
        return account;
    }

    public Account findAccount(String number) throws AccountNotFoundException {
        Account account = accounts.get(number);
        if (account == null) {
            throw new AccountNotFoundException("No account found with number " + number);
        }
        return account;
    }

    public void transfer(String fromNumber, String toNumber, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        Account from = findAccount(fromNumber);
        Account to = findAccount(toNumber);
        from.withdraw(amount);
        to.deposit(amount);
    }
}
