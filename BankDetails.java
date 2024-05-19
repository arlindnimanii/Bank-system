package com.example.bnaka20242;

import java.sql.*;
import java.util.Scanner;

public class BankDetails extends Account {
    private double flatFee;
    private double percentFee;
    private String bankName;

    public BankDetails(double flatFee, double percentFee, String bankName) {
        super("", "", "", 0.0); // Temporary values, will be set in openAccount()
        this.flatFee = flatFee;
        this.percentFee = percentFee;
        this.bankName = bankName;
    }

    public static BankDetails[] fetchUserDataFromDatabase() {
        return fetchAllUsers();
    }

    public void openAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Account No: ");
        this.accNo = sc.next();
        System.out.print("Enter Account type: ");
        this.accType = sc.next();
        System.out.print("Enter Name: ");
        this.name = sc.next();
        System.out.print("Enter Balance: ");
        this.balance = sc.nextDouble();

        // Save the account details to the database
        saveToDatabase();
    }

    @Override
    public void showAccount() {
        System.out.println("Account No: " + this.accNo);
        System.out.println("Account type: " + this.accType);
        System.out.println("Name: " + this.name);
        System.out.println("Balance: " + this.balance);
    }

    @Override
    public boolean search(String accNo) {
        if (this.accNo.equals(accNo)) {
            showAccount();
            return true;
        }
        return false;
    }

    @Override
    public double deposit(double amount, int feeType) {
        double fee = (feeType == 1) ? flatFee : (amount * percentFee / 100);
        this.balance += (amount - fee);
        updateBalanceInDatabase();
        saveTransaction("Deposit", amount, fee);
        return fee;
    }

    // Overloaded method for deposit without fee type (default to flat fee)
    public double deposit(double amount) {
        return deposit(amount, 1); // Default to flat fee
    }

    @Override
    public double withdrawal(double amount, int feeType) {
        double fee = (feeType == 1) ? flatFee : (amount * percentFee / 100);
        if (this.balance >= (amount + fee)) {
            this.balance -= (amount + fee);
            updateBalanceInDatabase();
            saveTransaction("Withdrawal", amount, fee);
            return fee;
        } else {
            System.out.println("Insufficient balance! Unable to process withdrawal.");
            return 0;
        }
    }

    // Overloaded method for withdrawal without fee type (default to flat fee)
    public double withdrawal(double amount) {
        return withdrawal(amount, 1); // Default to flat fee
    }

    public double transfer(BankDetails receiver, double amount, int feeType) {
        double fee = (feeType == 1) ? flatFee : (amount * percentFee / 100);
        if (this.balance >= (amount + fee)) {
            this.balance -= (amount + fee);
            receiver.balance += amount;
            updateBalanceInDatabase();
            receiver.updateBalanceInDatabase();
            saveTransaction("Transfer to " + receiver.accNo, amount, fee);
            receiver.saveTransaction("Transfer from " + this.accNo, amount, 0);
            return fee;
        } else {
            System.out.println("Insufficient balance! Unable to process transfer.");
            return 0;
        }
    }

    public void showTransactions() {
        // Fetch and display the list of transactions from the database
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_db", "root", "a.n8")) {
            String query = "SELECT * FROM transactions WHERE accno = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, this.accNo);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Transaction Type: " + rs.getString("type"));
                System.out.println("Amount: " + rs.getDouble("amount"));
                System.out.println("Fee: " + rs.getDouble("fee"));
                System.out.println("Date: " + rs.getTimestamp("timestamp"));
                System.out.println("----");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching transactions: " + e.getMessage());
        }
    }

    private void saveToDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_db", "root", "a.n8")) {
            String query = "INSERT INTO users (accno, name, acc_type, balance, bankName) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, this.accNo);
            pstmt.setString(2, this.name);
            pstmt.setString(3, this.accType);
            pstmt.setDouble(4, this.balance);
            pstmt.setString(5, this.bankName);
            pstmt.executeUpdate();
            System.out.println("Account created successfully!");
        } catch (SQLException e) {
            System.out.println("Error saving account to database: " + e.getMessage());
        }
    }

    private void updateBalanceInDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_db", "root", "a.n8")) {
            String query = "UPDATE users SET balance = ? WHERE accno = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1, this.balance);
            pstmt.setString(2, this.accNo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating balance: " + e.getMessage());
        }
    }

    private void saveTransaction(String type, double amount, double fee) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_db", "root", "a.n8")) {
            String query = "INSERT INTO transactions (accno, type, amount, fee) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, this.accNo);
            pstmt.setString(2, type);
            pstmt.setDouble(3, amount);
            pstmt.setDouble(4, fee);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    public static BankDetails[] fetchAllUsers() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_db", "root", "a.n8")) {
            String query = "SELECT * FROM users";
            PreparedStatement pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstmt.executeQuery();

            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();

            BankDetails[] accounts = new BankDetails[size];
            int index = 0;
            while (rs.next()) {
                String accNo = rs.getString("accno");
                String accType = rs.getString("acc_type");
                String name = rs.getString("name");
                double balance = rs.getDouble("balance");

                accounts[index++] = new BankDetails(0.0, 0.0, "Bank", accNo, accType, name, balance);
            }

            return accounts;
        } catch (SQLException e) {
            System.out.println("Error fetching users from database: " + e.getMessage());
            return new BankDetails[0];
        }
    }

    private BankDetails(double flatFee, double percentFee, String bankName, String accNo, String accType, String name, double balance) {
        super(accNo, name, accType, balance);
        this.flatFee = flatFee;
        this.percentFee = percentFee;
        this.bankName = bankName;
    }
}
