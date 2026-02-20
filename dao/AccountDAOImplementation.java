package com.sant.bankManagement.dao;

import com.sant.bankManagement.model.Account;
import com.sant.bankManagement.model.Customer;
import com.sant.bankManagement.model.Transaction;
import com.sant.bankManagement.util.DBConnection;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class AccountDAOImplementation implements AccountDAO{

    public static Connection connection = DBConnection.getConnection();
    private CustomerDAOImplementation customerDAO = new CustomerDAOImplementation();

    private int lastAccountNumber;
    public AccountDAOImplementation() {
        this.lastAccountNumber = getLastAccountNumber();
    }



    private int getLastAccountNumber() {
        String sql = "SELECT MAX(accountNumber) FROM account";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next() && rs.getInt(1) != 0) {
                return rs.getInt(1); // ← returns last used number
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 609040;
    }

    @Override
    public void save(int customerId, Account account) {
        account.setAccNum(++lastAccountNumber);
        String sql = "INSERT INTO account(accountNumber,accountType,balance,customerId) VALUES(?,?,?,?)";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,account.getAccNum(account.getAccId()));
            ps.setString(2,account.getAccType());
            ps.setDouble(3,account.getBalance(account.getAccNum(account.getAccId())));
            ps.setInt(4,customerId);

            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findByAccNum(int accNum) {
        String sql = "SELECT * FROM account WHERE accountNumber = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1,accNum);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Customer customer = customerDAO.findById(rs.getInt("customerId"));
                return new Account(
                        rs.getString("accoutType"),
                        rs.getDouble("balance"),
                        customer
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account";
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                Customer customer = customerDAO.findById(rs.getInt("customerId"));
                accounts.add(new Account(
                        rs.getInt("accountId"),
                        rs.getString("accountType"),
                        rs.getDouble("balance"),
                        customer
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    @Override
    public void addBalance(int accountNumber, double amount){
        String sql = "UPDATE account SET balance = balance + ? WHERE accountNumber = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1,amount);
            ps.setInt(2,accountNumber);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existAccount(int accountNumber){
        String sql = "SELECT COUNT(*) FROM account WHERE accountNumber = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,accountNumber);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public int getAccountNumber(int accId){
        String sql = "SELECT accountNumber FROM account WHERE accountId = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,accId);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getInt("accountNumber");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    @Override
    public double getBalance(int accNumber) {
        String sql = "SELECT balance FROM account WHERE accountNumber = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,accNumber);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getDouble("balance");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public boolean validWithdrawAmnt(int  accountNumber,double  amount){
        String sql = "SELECT balance FROM account WHERE accountNumber = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,accountNumber);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getDouble("balance") > amount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public void withdrawBalance(int accountNumber, double amount){
        String sql = "UPDATE account SET balance = balance - ? WHERE accountNumber = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1,amount);
            ps.setInt(2,accountNumber);

            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
