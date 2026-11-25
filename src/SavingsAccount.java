public class SavingsAccount extends Account {
    private static final double MIN_BALANCE = 500;
    private static final double INTEREST_RATE = 4;

    public SavingsAccount(String accountNumber, String holderName, double openingBalance) {
        super(accountNumber, holderName, openingBalance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than 0");
        }
        if (getBalance() - amount < MIN_BALANCE) {
            throw new InsufficientBalanceException("Minimum balance of " + MIN_BALANCE + " must be maintained");
        }
        setBalance(getBalance() - amount);
        addHistory("Withdrew " + amount + " | Balance: " + getBalance());
    }

    public void addInterest() {
        double interest = getBalance() * INTEREST_RATE / 100;
        setBalance(getBalance() + interest);
        addHistory("Interest added " + interest + " | Balance: " + getBalance());
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }
}
