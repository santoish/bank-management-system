package com.sant.bankManagement.ui;

import com.sant.bankManagement.model.Account;
import com.sant.bankManagement.model.Customer;

public class BankDisplay implements UIBankSystem{
    @Override
    public void displayMessage(String message) {
        System.out.println(message);
        System.out.println();
    }

    @Override
    public void customerInfo(Customer customer) {
        System.out.println();
        System.out.println("CUSTOMER INFORMATION");
        System.out.println("--------------------");
        System.out.println(customer.getName());
        System.out.println(customer.getAge());
        System.out.println(customer.getCustomerId());
        for(Account account : customer.getAccount()){
            System.out.println(account.getAccNum(account.getAccId()));
            System.out.println(account.getAccType());
            System.out.println(account.getBalance(account.getAccNum(account.getAccId())));
            if(account.getTransactionList(account.getAccNum(account.getAccId())) != null){
//                throw new BankExceptions("No Transaction");
                System.out.println(account.getTransactionList(account.getAccNum(account.getAccId())));
            }
        }
        System.out.println();
    }

    @Override
    public void displayError(Exception e) {
        System.out.println(e.getMessage());
        System.out.println();
    }
}
