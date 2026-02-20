package com.sant.bankManagement.ui;

import com.sant.bankManagement.model.Customer;

public interface UIBankSystem {
    void displayMessage(String message);
    void customerInfo(Customer customer);
    void displayError(Exception e);
}
