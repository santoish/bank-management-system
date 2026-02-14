package com.sant.bankManagement;

import java.util.ArrayList;
import java.util.List;

public interface BankManagement {
    void createCustomer(BankSystem bankSystem,Customer customer);
    void createAccount(BankSystem bankSystem,int customerId,Account account);
    void deposit(Customer customer,int accountNumber, double amount, String name);
    void withdraw(Customer customer,int accountNumber, double amount, String name);
    double checkBalance(BankSystem bankSystem,Customer customer, int accountNumber);
    void getCustomerInfo(BankSystem bankSystem,Customer customer);
    ArrayList<Transaction> transactionHistory(Customer customer, int accountNumber);
    Customer findCustomerById(BankSystem bankSystem,int customerId);
}
