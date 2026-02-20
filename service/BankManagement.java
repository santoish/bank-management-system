package com.sant.bankManagement.service;

import com.sant.bankManagement.model.Account;
import com.sant.bankManagement.model.BankSystem;
import com.sant.bankManagement.model.Customer;
import com.sant.bankManagement.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public interface BankManagement {
    void createCustomer(Customer customer);
    void createAccount(int customerId, Account account);
    void deposit(int accountNumber, double amount);
    void withdraw(int accountNumber, double amount);
    double checkBalance(int accountNumber);
    void getCustomerInfo(BankSystem bankSystem,Customer customer);
    List<Transaction> transactionHistory(int accountNumber);
    Customer findCustomerById(int customerId);
}
