package com.sant.bankManagement;

public interface ValidationRules {
    boolean validDepositAmount(double amount);
    boolean validName(String name);
    boolean validAge(int age);
    boolean validAccType(String accType);
    boolean validAccNum(int accNum, Customer customer);
    boolean validInitialBalance(String accType,double balance);
}
