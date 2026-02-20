package com.sant.bankManagement.model;

import com.sant.bankManagement.dao.AccountDAOImplementation;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private List<Account> accounts = new ArrayList<>();
    private int customerId;
    private AccountDAOImplementation accountDAO;

    public List<Account> getAccount() {
        return accounts;
    }

    public int getCustomerId() {
        return customerId;
    }

//    public void addAccount(Account customerAccount,int customerId){
//
//        accounts.add(customerAccount);
//    }

    public Customer(int customerId, String name, int age){
        super(name, age);
        this.customerId = customerId;
    }
}
