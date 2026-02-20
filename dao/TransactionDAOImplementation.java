package com.sant.bankManagement.dao;

import com.sant.bankManagement.model.Transaction;
import com.sant.bankManagement.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImplementation implements TransactionDAO{

    public static Connection connection = DBConnection.getConnection();

    @Override
    public void save(Transaction transaction) {
        String sql = "INSERT INTO transaction(trans_date,amount,type,accountId) VALUES(NOW(),?,?,?)";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1,transaction.getAmount());
            ps.setString(2,transaction.getType().name());
            ps.setInt(3,transaction.getAccountId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Transaction> findByAccNumber(int accountNum) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transaction WHERE accountId = ? ";
        String sql2 = "SELECT accountId FROM account WHERE accountNumber = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql2);
            ps.setInt(1,accountNum);
            ResultSet rs = ps.executeQuery();

            rs.next();

            int accountId = rs.getInt("accountId");
//            System.out.println(accountId);

            PreparedStatement ps1 = connection.prepareStatement(sql);
            ps1.setInt(1,accountId);
            ResultSet rs1 = ps1.executeQuery();

            while(rs1.next()){
                transactions.add(new Transaction(
                        rs1.getInt("trans_id"),
                        rs1.getDate("trans_date"),
                        rs1.getDouble("amount"),
                        Transaction.TransactionType.valueOf(rs1.getString("type")),
                        rs1.getInt("accountId")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }
}
