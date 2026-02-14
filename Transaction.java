package com.sant.bankManagement;

import java.time.LocalDate;

public class Transaction {
    private double amount;
    private TransactionType type;
    private LocalDate date;
    private String name;

    enum TransactionType{
        DEPOSIT,
        WITHDRAW
    }

    public Transaction(double amount, String name,TransactionType type) {
        this.amount = amount;
        this.name = name;
        this.date = LocalDate.now();
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", type=" + type +
                ", date=" + date +
                ", name='" + name + '\'' +
                '}';
    }
}
