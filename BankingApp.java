package com.example.bnaka20242;

import java.util.Scanner;

public class BankingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Do you want to create a user or proceed with the services of the bank? (create/proceed): ");
            String choice = sc.nextLine().toLowerCase();

            if (choice.equals("create")) {
                // If the user wants to create a user
                System.out.print("Enter bank name: ");
                String bankName = sc.nextLine();
                System.out.print("Enter flat fee: ");
                double flatFee = sc.nextDouble();
                System.out.print("Enter percentage fee: ");
                double percentFee = sc.nextDouble();
                sc.nextLine();  // consume the newline

                // Create initial accounts
                BankDetails account = new BankDetails(flatFee, percentFee, bankName);
                account.openAccount();

            } else if (choice.equals("proceed")) {
                // If the user wants to proceed with the services of the bank
                // Retrieve user data from the database
                BankDetails[] C = BankDetails.fetchUserDataFromDatabase();

                // Proceed with banking services
                processBankingServices(C, "Bank", sc);
                break; // Exit the loop after proceeding with banking services
            } else {
                System.out.println("Invalid choice! Please enter either 'create' or 'proceed'.");
            }
        }
    }
    private static void processBankingServices(BankDetails[] accounts, String bankName, Scanner sc) {
        double totalTransactionFee = 0.0;
        int choice;
        do {
            System.out.println("\n ***Banking System Application***");
            System.out.println("1. Display all account details");
            System.out.println("2. Search by Account number");
            System.out.println("3. Deposit amount");
            System.out.println("4. Withdraw amount");
            System.out.println("5. Transfer funds");
            System.out.println("6. See list of transactions for any account");
            System.out.println("7. Check bank total transaction fee amount");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    // Display all account details
                    for (BankDetails account : accounts) {
                        if (account != null) {
                            account.showAccount();
                        }
                    }
                    break;
                case 2:
                    // Search by account number
                    System.out.print("Enter account number you want to search: ");
                    String accNo = sc.nextLine();
                    boolean found = false;
                    for (BankDetails account : accounts) {
                        if (account != null && account.search(accNo)) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist.");
                    }
                    break;
                case 3:
                    // Deposit amount
                    System.out.print("Enter Account number: ");
                    accNo = sc.nextLine();
                    found = false;
                    for (BankDetails account : accounts) {
                        if (account != null && account.search(accNo)) {
                            System.out.print("Enter the amount you want to deposit (in dollars): ");
                            double depositAmt = sc.nextDouble();
                            System.out.println("Choose transaction fee type: 1. Flat Fee 2. Percentage Fee");
                            int feeChoice = sc.nextInt();
                            double fee = account.deposit(depositAmt, feeChoice);
                            System.out.println("Fee charged: $" + String.format("%.2f", fee));
                            totalTransactionFee += fee;
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Deposit failed! Account doesn't exist.");
                    }
                    break;
                case 4:
                    // Withdraw amount
                    System.out.print("Enter Account number: ");
                    accNo = sc.nextLine();
                    found = false;
                    for (BankDetails account : accounts) {
                        if (account != null && account.search(accNo)) {
                            System.out.print("Enter the amount you want to withdraw (in dollars): ");
                            double withdrawAmt = sc.nextDouble();
                            System.out.println("Choose transaction fee type: 1. Flat Fee 2. Percentage Fee");
                            int feeChoice = sc.nextInt();
                            double fee = account.withdrawal(withdrawAmt, feeChoice);
                            System.out.println("Fee charged: $" + String.format("%.2f", fee));
                            totalTransactionFee += fee;
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Withdrawal failed! Account doesn't exist.");
                    }
                    break;
                case 5:
                    // Transfer funds
                    System.out.print("Enter sender's Account No: ");
                    String senderAccNo = sc.nextLine();
                    boolean senderFound = false;
                    BankDetails senderAccount = null;
                    for (BankDetails account : accounts) {
                        if (account != null && account.search(senderAccNo)) {
                            senderAccount = account;
                            senderFound = true;
                            break;
                        }
                    }
                    if (!senderFound) {
                        System.out.println("Sender account not found.");
                        break;
                    }

                    System.out.print("Enter receiver's Account No: ");
                    String receiverAccNo = sc.nextLine();
                    boolean receiverFound = false;
                    BankDetails receiverAccount = null;
                    for (BankDetails account : accounts) {
                        if (account != null && account.search(receiverAccNo)) {
                            receiverAccount = account;
                            receiverFound = true;
                            break;
                        }
                    }
                    if (!receiverFound) {
                        System.out.println("Receiver account not found.");
                        break;
                    }

                    System.out.print("Enter the amount you want to transfer (in dollars): ");
                    double transferAmt = sc.nextDouble();
                    System.out.println("Choose transaction fee type: 1. Flat Fee 2. Percentage Fee");
                    int feeChoice = sc.nextInt();
                    double fee = senderAccount.transfer(receiverAccount, transferAmt, feeChoice);
                    System.out.println("Fee charged: $" + String.format("%.2f", fee));
                    totalTransactionFee += fee;
                    break;
                case 6:
                    // See list of transactions for any account
                    System.out.print("Enter account number: ");
                    accNo = sc.nextLine();
                    boolean foundTransactions = false;
                    for (BankDetails account : accounts) {
                        if (account != null && account.search(accNo)) {
                            account.showTransactions();
                            foundTransactions = true;
                            break;
                        }
                    }
                    if (!foundTransactions) {
                        System.out.println("No transactions found! Account doesn't exist.");
                    }
                    break;
                case 7:
                    // Check bank total transaction fee amount
                    System.out.println("Total transaction fees collected by the bank: $" + String.format("%.2f", totalTransactionFee));
                    break;
                case 8:
                    // Exit
                    System.out.println("Exiting... Thank you for using " + bankName + " Bank.");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        } while (choice != 8);
    }

}
