package com.sant.bankManagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        BankSystem bankSystem = new BankSystem();
        BankManagement bankManagement = new BankServices();
        UIBankSystem uiBankSystem = new BankDisplay();


        Customer customer = new Customer(101,"Santhosh",19);
        Customer customer2 = new Customer(102,"Bala",14);
        Customer customer3 = new Customer(103,"Sam",20);
        Customer customer4 = new Customer(104,"Sri",19);
        Customer customer5 = new Customer(101,"Santhosh",19);
        Customer customer6 = new Customer(102,"Bala",20);

        Customer[] customers = {customer,customer2,customer3,customer4,customer5,customer6};

        for(Customer customer1: customers){
            try{
                bankManagement.createCustomer(bankSystem,customer1);
                uiBankSystem.displayMessage(customer1.getName()+" Customer created Successfully");
            }catch (BankExceptions.InvalidCustomerException | BankExceptions.ValidationFailed e){
                uiBankSystem.displayError(e);
            }
        }

        System.out.println();

        Account sant = new Account("Savings",500,customer);
        Account bala = new Account("Savings",300,customer6);
        Account sam = new Account("Current",1500,customer3);
        Account sri = new Account("Current",500,customer4);



        try{
            bankManagement.createAccount(bankSystem,101,sant);
        } catch (Exception e) {
            uiBankSystem.displayError(e);
        }
        try{
            bankManagement.createAccount(bankSystem,102,bala);
        } catch (Exception e) {
            uiBankSystem.displayError(e);
        }
        try{
            bankManagement.createAccount(bankSystem,103,sam);
        } catch (Exception e) {
            uiBankSystem.displayError(e);
        }
        try{
            bankManagement.createAccount(bankSystem,104,sri);
        } catch (Exception e) {
            uiBankSystem.displayError(e);
        }


        System.out.println();

        int[] depositAccounts = {609041, 609044};

        for (int accNo : depositAccounts) {
            try {
                bankManagement.deposit(customer, accNo, 1000, customer.getName());
                uiBankSystem.displayMessage("Deposit successful for Account: " + accNo);
            } catch (Exception e) {
                uiBankSystem.displayError(e);
            }
        }


        int[] withdrawAccounts = {609041, 609044};
        double[] withdrawAmounts = {1000, 1000, 4000};

        for (int i = 0; i < withdrawAmounts.length; i++) {
            try {
                int accNo = (i == 2) ? 609041 : withdrawAccounts[i];
                bankManagement.withdraw(customer, accNo, withdrawAmounts[i], customer.getName());
                uiBankSystem.displayMessage("Withdraw successful for Account: " + accNo);
            } catch (Exception e) {
                uiBankSystem.displayError(e);
            }
        }

        try{
            uiBankSystem.displayMessage("Balance : "+bankManagement.checkBalance(bankSystem,customer,609041));
        } catch (Exception e) {
            uiBankSystem.displayError(e);
        }

        try{
            uiBankSystem.displayMessage("Balance : "+bankManagement.checkBalance(bankSystem,customer2,609041));
        } catch (BankExceptions.InvalidCustomerException e) {
            uiBankSystem.displayError(e);
        }

        try{
            ArrayList<Transaction> transactions = bankManagement.transactionHistory(customer,609041);
            for(Transaction transaction : transactions){
                uiBankSystem.displayMessage(transaction.toString());
            }
        } catch (Exception e) {
            uiBankSystem.displayError(e);
        }

    }
}
