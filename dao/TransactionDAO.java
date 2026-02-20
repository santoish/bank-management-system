package com.sant.bankManagement.dao;

import com.sant.bankManagement.model.Account;
import com.sant.bankManagement.model.Transaction;

import java.util.List;

public interface TransactionDAO {
    void save(Transaction transaction);
    List<Transaction> findByAccNumber(int accountNumber);
}
