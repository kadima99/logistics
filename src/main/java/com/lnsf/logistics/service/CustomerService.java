package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> selectAll(Integer delMark,String keyword,Integer status,Integer offset);

    Integer selectAllCountPage(Integer delMark,String keyword,Integer status);

    List<Customer> selectByStatus(Integer status, Integer offset);

    String login(String account, String password);

    List<Customer> selectByName(String name, Integer offset);

    Customer selectById(Integer id);

    Boolean forbidById(Integer id);

    Boolean recoverById(Integer id);

    Boolean resetPassword(Integer id);

    String insert(Customer record);

    String update(Customer record);

    String delete(Integer id);
}
