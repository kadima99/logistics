package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> selectAll(Integer offset);

    List<Customer> selectByStatus(Integer status,Integer offset);

    Customer selectByAccountAndPassword(String name,String password);

    List<Customer> selectByName(String name,Integer offset);

    Customer selectByAccount(String account);

    Customer selectById(Integer id);

    String insert(Customer record);

    String update(Customer record);

    String deleteById(Integer id);
}
