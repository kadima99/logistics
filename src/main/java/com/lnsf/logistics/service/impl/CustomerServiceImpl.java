package com.lnsf.logistics.service.impl;

import com.lnsf.logistics.entity.Customer;
import com.lnsf.logistics.mapper.CustomerMapper;
import com.lnsf.logistics.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.lnsf.logistics.Enum.CustomerStatus.FORBID;
import static com.lnsf.logistics.Enum.CustomerStatus.WAS_USING;

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
    public String login(String account, String password) {
        if (customerMapper.selectByAccount(account) != null) {
            if (customerMapper.selectByAccount(account).getStatus().equals(FORBID)) {
                return "你的账号已经被封禁！请联系客服寻求帮助！";
            } else if (customerMapper.selectByAccountAndPassword(account, password) != null) {
                return "登陆成功";
            } else return "密码错误！";
        } else return "账户不存在！";


    }


    @Override
    public Customer selectById(Integer id) {
        return customerMapper.selectById(id);
    }

    @Override
    public Boolean forbidById(Integer id){
        Customer customer = customerMapper.selectById(id);
        customer.setStatus(FORBID.getCode());
        return customerMapper.update(customer);
    }

    @Override
    public Boolean recoverById(Integer id){
        Customer customer = customerMapper.selectById(id);
        customer.setStatus(WAS_USING.getCode());
        return customerMapper.update(customer);
    }

    @Override
    public Boolean resetPassword(Integer id ){
        Customer customer = customerMapper.selectById(id);
        customer.setPassword("123456");
        return customerMapper.update(customer);
    }

    @Override
    public String insert(Customer record) {
        if (customerMapper.selectByAccount(record.getAccount()) != null) {
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
    public String delete(Integer id) {
        if (selectById(id) != null) {
            if (customerMapper.deleteById(id)) {
                return "删除成功";
            } else return "删除失败";
        } else return "id有误！";
    }
}
