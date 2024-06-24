import java.util.Scanner;

public class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to the ATM!");
        while (!exit) {
            System.out.println("ATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Please choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit(scanner);
                    break;
                case 3:
                    withdraw(scanner);
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    System.out.println("Thank you! Visit us next time.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private void checkBalance() {
        System.out.println("Your current balance is: $" + account.getBalance());
    }

    private void deposit(Scanner scanner) {
        System.out.print("Enter the account number: ");
        String accountNumber = scanner.next();
        if (verifyAccountNumber(scanner, accountNumber)) {
            System.out.print("Enter the amount to deposit: ");
            double amount = scanner.nextDouble();
            if (amount > 0) {
                account.deposit(amount);
                System.out.println("Successfully deposited $" + amount);
            } else {
                System.out.println("Invalid amount. Please try again.");
            }
        } else {
            System.out.println("Account verification failed. Please try again.");
        }
    }

    private void withdraw(Scanner scanner) {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && account.withdraw(amount)) {
            System.out.println("Successfully withdrew $" + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount. Please try again.");
        }
    }

    private boolean verifyAccountNumber(Scanner scanner, String accountNumber) {
        System.out.print("Re-enter the account number to verify: ");
        String verifyAccountNumber = scanner.next();
        return accountNumber.equals(verifyAccountNumber) && accountNumber.equals(account.getAccountNumber());
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000, "1234567890"); 
        ATM atm = new ATM(account);
        atm.start();
    }
}