package com.sant.bankManagement;

public class ValidatingMethods implements ValidationRules{
    @Override
    public boolean validDepositAmount(double amount) {
        return amount >= 500 && amount <= 10000;
    }


    @Override
    public boolean validName(String name) {
        return name != null && !name.isEmpty() ;
    }

    @Override
    public boolean validAge(int age) {
        return age > 18;
    }

    @Override
    public boolean validAccType(String accType) {
        return accType.equalsIgnoreCase("savings") || accType.equalsIgnoreCase("current");
    }

    @Override
    public boolean validAccNum(int accNum, Customer customer) {
        for(Account account : customer.getAccount()){
            if(account.getAccNum() == accNum){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validInitialBalance(String accType,double balance) {
        if(accType.equalsIgnoreCase("savings") && balance >= 500){
            return true;
        } else return accType.equalsIgnoreCase("current") && balance >= 1000;
    }

}
