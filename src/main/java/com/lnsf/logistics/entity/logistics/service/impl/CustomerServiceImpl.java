package com.lnsf.logistics.entity.logistics.service.impl;

import com.lnsf.logistics.entity.logistics.entity.Customer;
import com.lnsf.logistics.entity.logistics.mapper.CustomerMapper;
import com.lnsf.logistics.entity.logistics.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> selectAll() {
        return customerMapper.selectAll();
    }

    @Override
    public List<Customer> selectByStatus(Integer status) {
        return customerMapper.selectByStatus(status);
    }

    @Override
    public Customer selectByNameAndPassword(String name, String password) {
        return customerMapper.selectByNameAndPassword(name, password);
    }

    @Override
    public Customer selectByName(String name) {
        return customerMapper.selectByName(name);
    }

    @Override
    public Boolean insert(Customer record) {
        return customerMapper.insert(record);
    }

    @Override
    public Boolean update(Customer record) {
        return customerMapper.update(record);
    }

    @Override
    public Boolean deleteById(Integer id) {
        return customerMapper.deleteById(id);
    }
}
