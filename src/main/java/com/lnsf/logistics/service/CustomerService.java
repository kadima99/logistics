package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> selectAll();

    List<Customer> selectByState(Integer state);

    Customer selectByNameAndPassword(String name,String password);

    Customer selectByName(String name);

    Boolean insert(Customer record);

    Boolean update(Customer record);

    Boolean deleteById(Integer id);
}
