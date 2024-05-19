
package com.example.bnaka20242;

public abstract class Account {
    protected String accNo;
    protected String name;
    protected String accType;
    protected double balance;

    public Account(String accNo, String name, String accType, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.accType = accType;
        this.balance = balance;
    }

    public abstract void showAccount();
    public abstract boolean search(String accNo);
    public abstract double deposit(double amount, int feeType);
    public abstract double withdrawal(double amount, int feeType);
}
