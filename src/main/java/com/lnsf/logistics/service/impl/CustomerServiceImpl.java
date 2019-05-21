package com.lnsf.logistics.service.impl;

import com.lnsf.logistics.entity.Customer;
import com.lnsf.logistics.mapper.CustomerMapper;
import com.lnsf.logistics.service.CustomerService;
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
    public List<Customer> selectAll(Integer offset) {
        return customerMapper.selectAll(offset);
    }

    @Override
    public List<Customer> selectByStatus(Integer status, Integer offset) {
        return customerMapper.selectByStatus(status, offset);
    }

    @Override
    public List<Customer> selectByName(String name, Integer offset) {
        return customerMapper.selectByName(name, offset);
    }

    @Override
    public Customer selectByAccountAndPassword(String account, String password) {
        return customerMapper.selectByAccountAndPassword(account, password);
    }

    @Override
    public Customer selectByAccount(String account) {
        return customerMapper.selectByAccount(account);
    }


    @Override
    public Customer selectById(Integer id) {
        return customerMapper.selectById(id);
    }


    @Override
    public String insert(Customer record) {
        if (selectByAccount(record.getAccount()) != null) {
            return "该用户已存在！";
        } else if (customerMapper.insert(record)) {
            return "插入成功";
        } else return "插入失败";
    }

    @Override
    public String update(Customer record) {
             if (customerMapper.update(record)) {
                return "修改成功";
            } else return "修改失败";
    }

    @Override
    public String deleteById(Integer id) {
        if (selectById(id) != null) {
            if (customerMapper.selectById(id).getDelMark() == 1) {
                return "该用户已经删除！请勿重复操作";
            } else if (customerMapper.deleteById(id)) {
                return "删除成功";
            } else return "删除失败";
        } else return "id有误！";
    }
}
