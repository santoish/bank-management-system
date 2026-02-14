package com.sant.bankManagement;

public interface UIBankSystem {
    void displayMessage(String message);
    void customerInfo(Customer customer);
    void displayError(Exception e);
}
