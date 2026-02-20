package com.sant.bankManagement.service;

import com.sant.bankManagement.dao.AccountDAOImplementation;
import com.sant.bankManagement.dao.CustomerDAOImplementation;
import com.sant.bankManagement.ui.BankDisplay;
import com.sant.bankManagement.exceptions.BankExceptions;
import com.sant.bankManagement.model.Account;
import com.sant.bankManagement.model.BankSystem;
import com.sant.bankManagement.model.Customer;
import com.sant.bankManagement.model.Transaction;
import com.sant.bankManagement.validations.ValidatingMethods;

import java.util.ArrayList;
import java.util.List;

public class BankServices implements BankManagement{
//    private static int accountNumber = 609040;
    ValidatingMethods validatingMethods = new ValidatingMethods();
    BankDisplay bankDisplay = new BankDisplay();

    private CustomerDAOImplementation customerDAO = new CustomerDAOImplementation();
    private AccountDAOImplementation accountDAO = new AccountDAOImplementation();



    @Override
    public void createCustomer(Customer customer){

        ArrayList<Customer> customers = customerDAO.findAll();
        for(Customer customer1 : customers){
            if(customer1.getCustomerId() == (customer.getCustomerId())) {
                throw new BankExceptions.InvalidCustomerException(customer1.getName()+" This Customer Already Exist");
            }
        }

        if(validatingMethods.validName(customer.getName()) && validatingMethods.validAge(customer.getAge())){
//            bankSystem.getCustomerList().add(customer);
            customerDAO.save(customer);

        }
        else{
            throw new BankExceptions.ValidationFailed("Invalid Name or Invalid Age for Customer");
        }
    }

    @Override
    public void createAccount( int customerId, Account account) {

        Customer customer = findCustomerById(customerId);
        if(customer == null){
            throw new BankExceptions.InvalidCustomerException("Invalid customer");
        }

        List<Account> accountList = accountDAO.findAll();

        for(Account acc : accountList){
            if(acc.getAccType().equals(account.getAccType()) && acc.getOwner().getCustomerId() == customerId){
                throw new BankExceptions.DuplicateAccountException("This Account is already exists");
            }
        }

        if(!validatingMethods.validAccType(account.getAccType()) || !validatingMethods.validInitialBalance(account.getAccType(),account.getBalance(account.getAccNum(account.getAccId())))){
            throw new BankExceptions.ValidationFailed("Invalid Account Type or Invalid Initial Balance");
        }

        account.setOwner(customer);
        accountDAO.save(customerId,account);
//        customer.addAccount(account,customerId);
    }

    @Override
    public void deposit(int accountNumber, double amount) {
        if(!validatingMethods.validAccNum(accountNumber)){
            throw new BankExceptions.ValidationFailed("Invalid Account Number");
        }
        if(!validatingMethods.validDepositAmount(amount)){
            throw new BankExceptions.ValidationFailed("Invalid Amount");
        }

        Account account = findAccountNum(accountNumber);
        accountDAO.addBalance(accountNumber,amount);
        account.addTransactionList(amount, Transaction.TransactionType.DEPOSIT);
    }

    @Override
    public void withdraw(int accountNumber, double amount) {
        if(!validatingMethods.validAccNum(accountNumber)){
            throw new BankExceptions.ValidationFailed("Invalid Account Number");
        }
        if(!accountDAO.validWithdrawAmnt(accountNumber,amount)){
            throw new BankExceptions.ValidationFailed("Insufficient Balance");
        }

        Account account = findAccountNum(accountNumber);
//        if(account.getBalance(accountNumber) < amount){
//            throw new BankExceptions.InsufficientBalanceException(accountNumber);
//        }
        account.withdrawAmount(accountNumber,amount);
        account.addTransactionList(amount, Transaction.TransactionType.WITHDRAW);
    }

    @Override
    public double checkBalance(int accountNumber) {
        double balance = accountDAO.getBalance(accountNumber);
        return balance;
    }

    @Override
    public void getCustomerInfo(BankSystem bankSystem,Customer customer) {

        if(!bankSystem.getCustomerList().contains(customer)){
            throw new BankExceptions.InvalidCustomerException("The Given Customer not Exist");
        }

        bankDisplay.customerInfo(customer);

        // will return in ui

    }

    @Override
    public List<Transaction> transactionHistory( int accountNumber) {
        Account account = findAccountNum(accountNumber);
        if(account.getTransactionList(accountNumber).isEmpty()){
            throw new BankExceptions("Transaction History is Empty");
        }
        return account.getTransactionList(accountNumber);
    }

    @Override
    public Customer findCustomerById(int customerId) {
//        for(Customer customer : bankSystem.getCustomerList()){
//            if(customer.getCustomerId() == customerId){
//                return customer;
//            }
//        }
        return customerDAO.findById(customerId);
    }

    private Account findAccountNum(int accountNumber) {

        for(Account account : accountDAO.findAll()){
            if(account.getAccNum(account.getAccId()) == accountNumber){
                return account;
            }
        }
        throw new BankExceptions.InvalidCustomerException("The given AccNum is not exist");
    }

}
