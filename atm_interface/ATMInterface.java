import java.util.*;

class ATM {
    private double balance;
    private double previousTransaction;
    private String customerName;
    private String customerId;
    private List<String> transactionHistory = new ArrayList<>();

    public ATM(String customerName, String customerId) {
        this.customerName = customerName;
        this.customerId = customerId;
    }

    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            previousTransaction = amount;
            transactionHistory.add("Deposited: ₹" + amount);
            System.out.println("Successfully deposited ₹" + amount);
        } else {
            System.out.println("Enter amount greater than 0!");
        }
    }

    void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            previousTransaction = -amount;
            transactionHistory.add("Withdrawn: ₹" + amount);
            System.out.println("Successfully withdrawn ₹" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            System.out.println("Enter valid amount!");
        }
    }

    void transfer(double amount, String receiverId) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Transferred ₹" + amount + " to " + receiverId);
            System.out.println("Successfully transferred ₹" + amount + " to " + receiverId);
        } else {
            System.out.println("Transfer failed: invalid amount or insufficient balance.");
        }
    }

    void getPreviousTransaction() {
        if (previousTransaction > 0)
            System.out.println("Last Transaction: Deposited ₹" + previousTransaction);
        else if (previousTransaction < 0)
            System.out.println("Last Transaction: Withdrawn ₹" + Math.abs(previousTransaction));
        else
            System.out.println("No transaction occurred");
    }

    void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("\nTransaction History:");
            for (String record : transactionHistory) {
                System.out.println(record);
            }
        }
    }

    void showMenu() {
        char option;
        Scanner sc = new Scanner(System.in);

        System.out.println("\nWelcome, " + customerName + "!");
        System.out.println("Your ID: " + customerId);
        System.out.println();

        do {
            System.out.println("===========================");
            System.out.println("        ATM Menu");
            System.out.println("===========================");
            System.out.println("A. Transaction History");
            System.out.println("B. Deposit");
            System.out.println("C. Withdraw");
            System.out.println("D. Transfer");
            System.out.println("E. Check Previous Transaction");
            System.out.println("F. Quit");
            System.out.println("===========================");
            System.out.print("Enter an option: ");
            option = sc.next().toUpperCase().charAt(0);
            System.out.println();

            switch (option) {
                case 'A':
                    showTransactionHistory();
                    break;

                case 'B':
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = sc.nextDouble();
                    deposit(depositAmount);
                    break;

                case 'C':
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = sc.nextDouble();
                    withdraw(withdrawAmount);
                    break;

                case 'D':
                    System.out.print("Enter receiver ID: ");
                    String receiver = sc.next();
                    System.out.print("Enter amount to transfer: ₹");
                    double transferAmount = sc.nextDouble();
                    transfer(transferAmount, receiver);
                    break;

                case 'E':
                    getPreviousTransaction();
                    break;

                case 'F':
                    System.out.println("Exiting... Thank you for using our ATM service!");
                    break;

                default:
                    System.out.println("Invalid option! Please try again.");
                    break;
            }

            System.out.println();
        } while (option != 'F');

        sc.close();
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=======================================");
        System.out.println("          WELCOME TO THE ATM           ");
        System.out.println("=======================================");
        System.out.print("Enter your Name: ");
        String name = sc.nextLine();
        System.out.print("Enter your Customer ID: ");
        String id = sc.nextLine();

        ATM obj = new ATM(name, id);
        obj.showMenu();

        sc.close();
    }
}
