import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("===== BANK MENU =====");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Current Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Check Balance / Details");
            System.out.println("6. Transfer Money");
            System.out.println("7. Transaction History");
            System.out.println("8. Add Interest (Savings only)");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");

            String choice = sc.nextLine().trim();

            try {
                switch (choice) {
                    case "1": {
                        System.out.print("Holder name: ");
                        String name = sc.nextLine();
                        System.out.print("Opening balance: ");
                        double amount = Double.parseDouble(sc.nextLine());
                        Account acc = bank.createSavingsAccount(name, amount);
                        System.out.println("Account created. Number: " + acc.getAccountNumber());
                        break;
                    }
                    case "2": {
                        System.out.print("Holder name: ");
                        String name = sc.nextLine();
                        System.out.print("Opening balance: ");
                        double amount = Double.parseDouble(sc.nextLine());
                        Account acc = bank.createCurrentAccount(name, amount);
                        System.out.println("Account created. Number: " + acc.getAccountNumber());
                        break;
                    }
                    case "3": {
                        System.out.print("Account number: ");
                        Account acc = bank.findAccount(sc.nextLine().trim());
                        System.out.print("Amount: ");
                        acc.deposit(Double.parseDouble(sc.nextLine()));
                        System.out.println("Deposit successful. Balance: " + acc.getBalance());
                        break;
                    }
                    case "4": {
                        System.out.print("Account number: ");
                        Account acc = bank.findAccount(sc.nextLine().trim());
                        System.out.print("Amount: ");
                        acc.withdraw(Double.parseDouble(sc.nextLine()));
                        System.out.println("Withdrawal successful. Balance: " + acc.getBalance());
                        break;
                    }
                    case "5": {
                        System.out.print("Account number: ");
                        Account acc = bank.findAccount(sc.nextLine().trim());
                        acc.showDetails();
                        break;
                    }
                    case "6": {
                        System.out.print("From account number: ");
                        String from = sc.nextLine().trim();
                        System.out.print("To account number: ");
                        String to = sc.nextLine().trim();
                        System.out.print("Amount: ");
                        double amount = Double.parseDouble(sc.nextLine());
                        bank.transfer(from, to, amount);
                        System.out.println("Transfer successful.");
                        break;
                    }
                    case "7": {
                        System.out.print("Account number: ");
                        Account acc = bank.findAccount(sc.nextLine().trim());
                        for (String entry : acc.getHistory()) {
                            System.out.println("- " + entry);
                        }
                        break;
                    }
                    case "8": {
                        System.out.print("Account number: ");
                        Account acc = bank.findAccount(sc.nextLine().trim());
                        if (acc instanceof SavingsAccount) {
                            ((SavingsAccount) acc).addInterest();
                            System.out.println("Interest added. Balance: " + acc.getBalance());
                        } else {
                            System.out.println("Interest is only for Savings accounts.");
                        }
                        break;
                    }
                    case "9":
                        running = false;
                        System.out.println("Thank you for using the bank system.");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (InsufficientBalanceException | AccountNotFoundException | InvalidAmountException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }
        sc.close();
    }
}
