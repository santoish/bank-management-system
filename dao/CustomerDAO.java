package com.sant.bankManagement.dao;

import com.sant.bankManagement.model.Customer;

import java.util.List;

public interface CustomerDAO {
    void save(Customer customer);
    Customer findById(int customerId);
    List<Customer> findAll();
}
