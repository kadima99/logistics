package com.lnsf.logistics.entity.logistics.service;

import com.lnsf.logistics.entity.logistics.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> selectAll();

    List<Customer> selectByStatus(Integer status);

    Customer selectByNameAndPassword(String name, String password);

    Customer selectByName(String name);

    Boolean insert(Customer record);

    Boolean update(Customer record);

    Boolean deleteById(Integer id);
}
