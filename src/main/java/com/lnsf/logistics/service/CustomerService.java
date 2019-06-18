package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    List<Customer> selectAll(Integer delMark,String keyword,Integer status,Integer offset);

    Integer selectAllCountPage(Integer delMark,String keyword,Integer status);

    List<Customer> selectByStatus(Integer status, Integer offset);

    String login(String account, String password);

    List<Customer> selectByName(String name, Integer offset);

    Customer selectByAccount(String account);

    Customer selectById(Integer id);

    Map<String, Object> forbidById(List<Long> customerId);

    Map<String, Object> recoverById(List<Long> customerId);

    Boolean resetPassword(Integer id);

    String insert(Customer record);

    String update(Customer record);

    String delete(Integer id);
}
