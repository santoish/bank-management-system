package com.sant.bankManagement.dao;

import com.sant.bankManagement.model.Customer;
import com.sant.bankManagement.util.DBConnection;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImplementation implements CustomerDAO{

    public static Connection connection = DBConnection.getConnection();

    @Override
    public void save(Customer customer) {
        String sql = "INSERT INTO customers(customerId,name,age) VALUES(?,?,?)";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1,customer.getCustomerId());
            ps.setString(2,customer.getName());
            ps.setInt(3,customer.getAge());

            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer findById(int customerId) {
        String sql = "SELECT * FROM customers WHERE customerId = ?";
        try{
            PreparedStatement ps =connection.prepareStatement(sql);
            ps.setInt(1,customerId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return new Customer(
                        rs.getInt("customerId"),
                        rs.getString("name"),
                        rs.getInt("age")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public ArrayList<Customer> findAll() {
        String sql = "SELECT * FROM CUSTOMERS";
        ArrayList<Customer> tempCustomerList = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                tempCustomerList.add(new Customer(
                        rs.getInt("customerId"),
                        rs.getString("name"),
                        rs.getInt("age"))
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tempCustomerList;
    }
}
