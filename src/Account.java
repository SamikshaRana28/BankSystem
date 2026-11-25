import java.util.ArrayList;
import java.util.List;

public abstract class Account implements Transaction {
    private final String accountNumber;
    private final String holderName;
    private double balance;
    private final List<String> history;

    public Account(String accountNumber, String holderName, double openingBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = openingBalance;
        this.history = new ArrayList<>();
        history.add("Account opened with " + openingBalance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    protected void addHistory(String entry) {
        history.add(entry);
    }

    public List<String> getHistory() {
        return history;
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than 0");
        }
        balance += amount;
        addHistory("Deposited " + amount + " | Balance: " + balance);
    }

    @Override
    public abstract void withdraw(double amount) throws InsufficientBalanceException;

    public abstract String getAccountType();

    public void showDetails() {
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Holder Name    : " + holderName);
        System.out.println("Account Type   : " + getAccountType());
        System.out.println("Balance        : " + balance);
    }
}
