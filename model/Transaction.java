package com.sant.bankManagement.model;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {
    private int transactionId;
    private double amount;
    private TransactionType type;
    private LocalDate date;
    private String name;
    private int accountId;

    public enum TransactionType{
        DEPOSIT,
        WITHDRAW
    }

    public TransactionType getType() {
        return type;
    }

    public Transaction(double amount, TransactionType type,int  accountId) {
        this.amount = amount;
        this.date = LocalDate.now();
        this.type = type;
        this.accountId = accountId;
    }

    public Transaction(int transactionId,Date date , double amount,  TransactionType type, int  accountId) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.date = LocalDate.now();
        this.type = type;
        this.accountId = accountId;
    }



    public double getAmount() {
        return amount;
    }

    public int getAccountId() {
        return accountId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", type=" + type +
                ", date=" + date +
                ", accountId= "+accountId+
                '}';
    }
}
