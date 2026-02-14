package com.sant.bankManagement;

public class BankExceptions extends RuntimeException {
    public BankExceptions(String message) {
        super(message);
    }

    public static class AccountNotFoundException extends BankExceptions{

        public AccountNotFoundException(int accNo){
            super(accNo+"This Account Number not Found\nTry Different Number");
        }
    }
    public static class InsufficientBalanceException extends BankExceptions{
        public InsufficientBalanceException(int accNo) {
            super("Insufficient Balance for this "+accNo);
        }
    }

    public static class InvalidAmountException extends BankExceptions{
        public InvalidAmountException(int accNo) {
            super("Invalid Amount in this Account"+accNo);
        }
    }

    public static class InvalidCustomerException extends BankExceptions{

        public InvalidCustomerException(String message) {
            super(message);
        }
    }

    public static class DuplicateAccountException extends BankExceptions{

        public DuplicateAccountException(String message) {
            super(message);
        }
    }

    public static class ValidationFailed extends BankExceptions{

        public ValidationFailed() {
            super("The Validation of Fields Failed\nSo Try Again");
        }
    }



}
