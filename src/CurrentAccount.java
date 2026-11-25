public class CurrentAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 5000;

    public CurrentAccount(String accountNumber, String holderName, double openingBalance) {
        super(accountNumber, holderName, openingBalance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than 0");
        }
        if (getBalance() - amount < -OVERDRAFT_LIMIT) {
            throw new InsufficientBalanceException("Overdraft limit of " + OVERDRAFT_LIMIT + " exceeded");
        }
        setBalance(getBalance() - amount);
        addHistory("Withdrew " + amount + " | Balance: " + getBalance());
    }

    @Override
    public String getAccountType() {
        return "Current";
    }
}
