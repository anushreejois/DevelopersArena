import java.util.Scanner;

class BankAccount {
    // Private fields for encapsulation
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String accountType;
    private static int accountCounter = 1000; // For auto-generating account numbers

    // Default Constructor
    public BankAccount() {
        this.accountNumber = "ACC" + (++accountCounter);
        this.accountHolderName = "Unknown";
        this.balance = 0.0;
        this.accountType = "Savings";
        System.out.println("Account created with default values");
    }

    // Parameterized Constructor
    public BankAccount(String accountHolderName, double initialBalance, String accountType) {
        this.accountNumber = "ACC" + (++accountCounter);
        setAccountHolderName(accountHolderName);
        setBalance(initialBalance);
        setAccountType(accountType);
        System.out.println("Account created successfully!");
        System.out.println("Account Number: " + this.accountNumber);
    }

    // Getter Methods
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    // Setter Methods with validation
    public void setAccountHolderName(String accountHolderName) {
        if (accountHolderName != null && !accountHolderName.trim().isEmpty()) {
            this.accountHolderName = accountHolderName.trim();
        } else {
            this.accountHolderName = "Unknown";
            System.out.println("Invalid name. Set to 'Unknown'");
        }
    }

    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            this.balance = 0.0;
            System.out.println("Invalid balance. Set to 0.0");
        }
    }

    public void setAccountType(String accountType) {
        if (accountType != null && (accountType.equalsIgnoreCase("Savings") ||
                accountType.equalsIgnoreCase("Current") ||
                accountType.equalsIgnoreCase("Checking"))) {
            this.accountType = accountType;
        } else {
            this.accountType = "Savings";
            System.out.println("Invalid account type. Set to 'Savings'");
        }
    }

    // Deposit Method
    public boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("✅ Deposit Successful!");
            System.out.println("Amount Deposited: $" + String.format("%.2f", amount));
            System.out.println("New Balance: $" + String.format("%.2f", this.balance));
            return true;
        } else {
            System.out.println("❌ Deposit Failed!");
            System.out.println("Amount must be greater than zero.");
            return false;
        }
    }

    // Withdraw Method
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Withdrawal Failed!");
            System.out.println("Amount must be greater than zero.");
            return false;
        }

        if (amount > this.balance) {
            System.out.println("❌ Withdrawal Failed!");
            System.out.println("Insufficient funds!");
            System.out.println("Current Balance: $" + String.format("%.2f", this.balance));
            System.out.println("Requested Amount: $" + String.format("%.2f", amount));
            return false;
        }

        this.balance -= amount;
        System.out.println("✅ Withdrawal Successful!");
        System.out.println("Amount Withdrawn: $" + String.format("%.2f", amount));
        System.out.println("Remaining Balance: $" + String.format("%.2f", this.balance));
        return true;
    }

    // Check Balance Method
    public void checkBalance() {
        System.out.println("💰 Current Balance: $" + String.format("%.2f", this.balance));
    }

    // Transfer Money Method
    public boolean transferMoney(BankAccount targetAccount, double amount) {
        if (targetAccount == null) {
            System.out.println("❌ Transfer Failed! Invalid target account.");
            return false;
        }

        if (this.withdraw(amount)) {
            targetAccount.deposit(amount);
            System.out.println("💸 Transfer completed from " + this.accountNumber +
                    " to " + targetAccount.getAccountNumber());
            return true;
        }
        return false;
    }

    // Display Account Information
    public void displayAccountInfo() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("         ACCOUNT INFORMATION");
        System.out.println("=".repeat(40));
        System.out.println("Account Number    : " + accountNumber);
        System.out.println("Account Holder    : " + accountHolderName);
        System.out.println("Account Type      : " + accountType);
        System.out.println("Current Balance   : $" + String.format("%.2f", balance));
        System.out.println("=".repeat(40));
    }

    // Add Interest Method (for savings accounts)
    public void addInterest(double interestRate) {
        if (accountType.equalsIgnoreCase("Savings")) {
            double interest = balance * (interestRate / 100);
            balance += interest;
            System.out.println("💎 Interest Added: $" + String.format("%.2f", interest));
            System.out.println("New Balance: $" + String.format("%.2f", balance));
        } else {
            System.out.println("Interest only applicable for Savings accounts.");
        }
    }
}

// Main Class with Interactive Menu - CHANGED NAME!
class BankAccountSystem {  // ✅ Different name now!
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount[] accounts = new BankAccount[10]; // Array to store multiple accounts
        int accountCount = 0;

        System.out.println("🏦 Welcome to Simple Bank System!");
        System.out.println("=".repeat(50));

        while (true) {
            displayMenu();
            System.out.print("Enter your choice (1-8): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Create new account
                    if (accountCount < 10) {
                        System.out.print("Enter account holder name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter initial balance: $");
                        double initialBalance = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter account type (Savings/Current/Checking): ");
                        String type = scanner.nextLine();

                        accounts[accountCount] = new BankAccount(name, initialBalance, type);
                        accountCount++;
                    } else {
                        System.out.println("Maximum account limit reached!");
                    }
                    break;

                case 2:
                    // Deposit money
                    BankAccount depositAccount = selectAccount(accounts, accountCount, scanner);
                    if (depositAccount != null) {
                        System.out.print("Enter deposit amount: $");
                        double depositAmount = scanner.nextDouble();
                        depositAccount.deposit(depositAmount);
                    }
                    break;

                case 3:
                    // Withdraw money
                    BankAccount withdrawAccount = selectAccount(accounts, accountCount, scanner);
                    if (withdrawAccount != null) {
                        System.out.print("Enter withdrawal amount: $");
                        double withdrawAmount = scanner.nextDouble();
                        withdrawAccount.withdraw(withdrawAmount);
                    }
                    break;

                case 4:
                    // Check balance
                    BankAccount balanceAccount = selectAccount(accounts, accountCount, scanner);
                    if (balanceAccount != null) {
                        balanceAccount.checkBalance();
                    }
                    break;

                case 5:
                    // Display account info
                    BankAccount displayAccount = selectAccount(accounts, accountCount, scanner);
                    if (displayAccount != null) {
                        displayAccount.displayAccountInfo();
                    }
                    break;

                case 6:
                    // Transfer money
                    if (accountCount >= 2) {
                        System.out.println("Select source account:");
                        BankAccount sourceAccount = selectAccount(accounts, accountCount, scanner);
                        System.out.println("Select target account:");
                        BankAccount targetAccount = selectAccount(accounts, accountCount, scanner);

                        if (sourceAccount != null && targetAccount != null && sourceAccount != targetAccount) {
                            System.out.print("Enter transfer amount: $");
                            double transferAmount = scanner.nextDouble();
                            sourceAccount.transferMoney(targetAccount, transferAmount);
                        } else {
                            System.out.println("Invalid account selection!");
                        }
                    } else {
                        System.out.println("Need at least 2 accounts for transfer!");
                    }
                    break;

                case 7:
                    // Add interest
                    BankAccount interestAccount = selectAccount(accounts, accountCount, scanner);
                    if (interestAccount != null) {
                        System.out.print("Enter interest rate (%): ");
                        double rate = scanner.nextDouble();
                        interestAccount.addInterest(rate);
                    }
                    break;

                case 8:
                    // Exit
                    System.out.println("Thank you for using Simple Bank System! 👋");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    // Helper method to display menu
    public static void displayMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("              BANK MENU");
        System.out.println("=".repeat(50));
        System.out.println("1. Create New Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Check Balance");
        System.out.println("5. Display Account Info");
        System.out.println("6. Transfer Money");
        System.out.println("7. Add Interest");
        System.out.println("8. Exit");
        System.out.println("=".repeat(50));
    }

    // Helper method to select account
    public static BankAccount selectAccount(BankAccount[] accounts, int count, Scanner scanner) {
        if (count == 0) {
            System.out.println("No accounts available! Create an account first.");
            return null;
        }

        System.out.println("\nAvailable Accounts:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + accounts[i].getAccountNumber() +
                    " - " + accounts[i].getAccountHolderName());
        }

        System.out.print("Select account (1-" + count + "): ");
        int choice = scanner.nextInt();

        if (choice >= 1 && choice <= count) {
            return accounts[choice - 1];
        } else {
            System.out.println("Invalid selection!");
            return null;
        }
    }
}
