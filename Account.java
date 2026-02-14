package com.sant.bankManagement;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private int accNum;
    private String accType;
    private double balance;
    private ArrayList<Transaction> transactionList = new ArrayList<>();
    private  Customer  owner;

    int getAccNum(){return this.accNum;}
    String getAccType(){return this.accType;}
    double getBalance(){return this.balance;}
    ArrayList<Transaction> getTransactionList(){return this.transactionList;}
    Customer getOwner(){return this.owner;}

    public void setOwner(Customer owner){
        this.owner = owner;
    }

    public void setAccNum(int accNum) {
        this.accNum = accNum;
    }

    public void addBalance(double amount){
        this.balance += amount;
    }

    public void withdrawAmount(double amount){
        this.balance -= amount;
    }

    public void addTransactionList(double amount, String name, Transaction.TransactionType transactionType){
        this.transactionList.add(new Transaction(amount,name,transactionType));
    }


    public Account(String accType, double balance, Customer owner) {
        this.accType = accType;
        this.balance = balance;
        this.owner = owner;
    }
}
