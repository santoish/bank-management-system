package com.sant.bankManagement;

import java.util.ArrayList;

public class BankServices implements BankManagement{
    private static int accountNumber = 609040;
    ValidatingMethods validatingMethods = new ValidatingMethods();
    BankDisplay bankDisplay = new BankDisplay();


    @Override
    public void createCustomer(BankSystem bankSystem,Customer customer){

        for(Customer customer1 : bankSystem.getCustomerList()){
            if(customer1.getCustomerId() == (customer.getCustomerId())) {
                throw new BankExceptions.InvalidCustomerException(customer1.getName()+" This Customer Already Exist");
            }
        }

        if(validatingMethods.validName(customer.getName()) && validatingMethods.validAge(customer.getAge())){
            bankSystem.getCustomerList().add(customer);
        }
        else{
            throw new BankExceptions.ValidationFailed();
        }
    }

    @Override
    public void createAccount(BankSystem
             bankSystem,int customerId,Account account) {

        Customer customer = findCustomerById(bankSystem,customerId);
        if(customer == null){
            throw new BankExceptions.InvalidCustomerException("Invalid customer");
        }

        for(Account acc : customer.getAccount()){
            if(acc.getAccType().equals(account.getAccType())){
                throw new BankExceptions.DuplicateAccountException("Account type already exists");
            }
        }

        if(!validatingMethods.validAccType(account.getAccType()) || !validatingMethods.validInitialBalance(account.getAccType(),account.getBalance())){
            throw new BankExceptions.ValidationFailed();
        }

        account.setOwner(customer);
        account.setAccNum(++accountNumber);
        customer.addAccount(account);
    }

    @Override
    public void deposit(Customer customer,int accountNumber, double amount, String name) {
        if(!validatingMethods.validAccNum(accountNumber,customer)){
            throw new BankExceptions.ValidationFailed();
        }
        if(!validatingMethods.validDepositAmount(amount)){
            throw new BankExceptions.ValidationFailed();
        }

        Account account = findAccountNum(customer,accountNumber);
        account.addBalance(amount);
        account.addTransactionList(amount,name, Transaction.TransactionType.DEPOSIT);
    }

    @Override
    public void withdraw(Customer customer,int accountNumber, double amount, String name) {
        if(!validatingMethods.validAccNum(accountNumber,customer)){
            throw new BankExceptions.ValidationFailed();
        }
        if(!validatingMethods.validDepositAmount(amount)){
            throw new BankExceptions.ValidationFailed();
        }

        Account account = findAccountNum(customer,accountNumber);
        if(account.getBalance() < amount){
            throw new BankExceptions.InsufficientBalanceException(accountNumber);
        }
        account.withdrawAmount(amount);
        account.addTransactionList(amount,name, Transaction.TransactionType.WITHDRAW);
    }

    @Override
    public double checkBalance(BankSystem bankSystem,Customer customer, int accountNumber) {
        Account account = findAccountNum(customer,accountNumber);
        double balance = account.getBalance();
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
    public ArrayList<Transaction> transactionHistory(Customer customer, int accountNumber) {
        Account account = findAccountNum(customer,accountNumber);
        if(account.getTransactionList().isEmpty()){
            throw new BankExceptions("Transaction History is Empty");
        }
        return account.getTransactionList();
    }

    @Override
    public Customer findCustomerById(BankSystem bankSystem,int customerId) {
        for(Customer customer : bankSystem.getCustomerList()){
            if(customer.getCustomerId() == customerId){
                return customer;
            }
        }
        throw new BankExceptions.InvalidCustomerException(customerId+" the give Id is not exist");
    }

    private Account findAccountNum(Customer customer,int accountNumber) {
        for(Account account : customer.getAccount()){
            if(account.getAccNum() == accountNumber){
                return account;
            }
        }
        throw new BankExceptions.InvalidCustomerException("The given AccNum is not exist");
    }

}
