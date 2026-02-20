package com.sant.bankManagement.validations;

import com.sant.bankManagement.model.Customer;

public interface ValidationRules {
    boolean validDepositAmount(double amount);
    boolean validName(String name);
    boolean validAge(int age);
    boolean validAccType(String accType);
    boolean validAccNum(int accNum);
    boolean validInitialBalance(String accType,double balance);
}
