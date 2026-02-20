package com.sant.bankManagement;

import com.sant.bankManagement.exceptions.BankExceptions;
import com.sant.bankManagement.model.Account;
import com.sant.bankManagement.model.BankSystem;
import com.sant.bankManagement.model.Customer;
import com.sant.bankManagement.model.Transaction;
import com.sant.bankManagement.ui.BankDisplay;
import com.sant.bankManagement.service.BankManagement;
import com.sant.bankManagement.service.BankServices;
import com.sant.bankManagement.ui.UIBankSystem;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BankManagement bankManagement = new BankServices();
        UIBankSystem uiBankSystem = new BankDisplay();

        System.out.println("\n---------CREATING CUSTOMERS---------\n");

        Customer santhosh = new Customer(101,"Santhosh",19);
        Customer bala = new Customer(102,"Bala",14);
        Customer sam = new Customer(103,"Sam",20);
        Customer sri = new Customer(104,"Sri",19);
        Customer santhoshDuplicate = new Customer(101,"Santhosh",19);
        Customer balaUpdated = new Customer(102,"Bala",20);
//
        Customer[] customers = {santhosh,bala,sam,sri,santhoshDuplicate,balaUpdated};

//        Create Customers

        for(Customer customer1: customers){
            try{
                bankManagement.createCustomer(customer1);
                uiBankSystem.displayMessage(customer1.getName()+" Customer created Successfully");
            }catch (BankExceptions.InvalidCustomerException | BankExceptions.ValidationFailed e){
                uiBankSystem.displayError(e);
            }
            catch (Exception e){
                uiBankSystem.displayError(e);
            }
        }

        System.out.println("\n---------CREATING ACCOUNTS---------\n");

        Account santhoshAccount = new Account("Savings",500,santhosh);
        Account balaAccount = new Account("Savings",500,balaUpdated);
        Account samAccount = new Account("Current",1500,sam);
        Account sriAccount = new Account("Current",2500,sri);

        Object[][] accountData = {
                {101, santhoshAccount, santhosh.getName()},
                {102, balaAccount, balaUpdated.getName()},
                {103, samAccount, sam.getName()},
                {104, sriAccount, sri.getName()}
        };

        for (Object[] data : accountData) {
            try {
                bankManagement.createAccount((int) data[0], (Account) data[1]);
                uiBankSystem.displayMessage("Account created for " + data[2]);
            } catch (Exception e) {
                uiBankSystem.displayError(e);
            }
        }

        System.out.println("\n---------Deposit Operations---------\n");

        int[] depositAccounts = {609042, 609043};

        for (int accNo : depositAccounts) {
            try {
                bankManagement.deposit(accNo, 2000);
                uiBankSystem.displayMessage("Deposit successful for Account: " + accNo);
            } catch (Exception e) {
                uiBankSystem.displayError(e);
            }
        }

        System.out.println("\n---------Withdrawal Operations---------\n");

        int[] withdrawAccounts = {609042, 609044};
        double[] withdrawAmounts = {500, 200, 4000};

        for (int i = 0; i < withdrawAmounts.length; i++) {
            try {
                int accNo = (i == 2) ? 609041 : withdrawAccounts[i];
                bankManagement.withdraw(accNo, withdrawAmounts[i]);
                uiBankSystem.displayMessage("Withdraw successful for Account: " + accNo);
            } catch (Exception e) {
                uiBankSystem.displayError(e);
            }
        }

        System.out.println("\n---------Transaction History---------\n");

        try{
            List<Transaction> transactions = bankManagement.transactionHistory(609042);
            for(Transaction transaction : transactions){
                uiBankSystem.displayMessage(transaction.toString());
            }
        } catch (Exception e) {
            uiBankSystem.displayError(e);
        }

    }
}
