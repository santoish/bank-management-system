package com.sant.bankManagement.dao;

import com.sant.bankManagement.model.Account;
import com.sant.bankManagement.model.Customer;

import java.util.List;

public interface AccountDAO {
    void save(int customerId, Account account);
    Account findByAccNum(int accNum);
    boolean existAccount(int accountNumber);
    int getAccountNumber(int accId);
    double getBalance(int accNumber);
    List<Account> findAll();
    void addBalance(int accountNumber,double amount);
    void withdrawBalance(int accountNumber, double amount);
    boolean validWithdrawAmnt(int  accountNumber,double  amount);
}
