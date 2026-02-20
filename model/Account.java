package com.sant.bankManagement.model;

import com.sant.bankManagement.dao.AccountDAOImplementation;
import com.sant.bankManagement.dao.TransactionDAOImplementation;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private int accId;
    private int accNum;
    private String accType;
    private double balance;
    private ArrayList<Transaction> transactionList = new ArrayList<>();
    private  Customer  owner;

    private AccountDAOImplementation accountDAO = new AccountDAOImplementation();
    private TransactionDAOImplementation transactionDAO = new TransactionDAOImplementation();

    public int getAccNum(int accId){return  accountDAO.getAccountNumber(accId);}

    public int getAccId() {
        return accId;
    }

    public String getAccType(){return this.accType;}
    public double getBalance(int ac){return this.balance;}
    public List<Transaction> getTransactionList(int accNum){return transactionDAO.findByAccNumber(accNum) ;}
    public Customer getOwner(){return this.owner;}

    public void setOwner(Customer owner){
        this.owner = owner;
    }

    public void setAccNum(int accNum) {

    }

    public void addBalance(int accNum,double amount){
        this.balance += amount;
    }

    public void withdrawAmount(int accNum,double amount){
        accountDAO.withdrawBalance(accNum,amount);
    }

    public void addTransactionList(double amount, Transaction.TransactionType transactionType){
        Transaction transaction = new Transaction(amount,transactionType,this.accId);
        transactionDAO.save(transaction);
//        this.transactionList.add(transaction);
    }


    public Account(String accType, double balance, Customer owner) {
        this.accType = accType;
        this.balance = balance;
        this.owner = owner;
    }

    public Account(int accountId, String accountType, double balance, Customer customer) {
        this.accId = accountId;
        this.accType = accountType;
        this.balance = balance;
        this.owner = customer;
    }
}
