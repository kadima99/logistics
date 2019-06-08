package com.lnsf.logistics.controller;

import com.lnsf.logistics.entity.Customer;
import com.lnsf.logistics.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lnsf.logistics.Enum.CustomerStatus.FORBID;
import static com.lnsf.logistics.Enum.CustomerStatus.WAS_USING;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/getAll")
    public Map<String, Object> selectAll(String keyword, Integer status, Integer page) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Customer> customer = customerService.selectAll(0, keyword, status, (page - 1) * 8);
        map.put("customerData", customer);
        double totalPage = Math.ceil(customerService.selectAllCountPage(0, keyword, status) / 8.0);
        map.put("totalPage", totalPage);
        return map;
    }


    @RequestMapping("/getAllHistory")
    public Map<String, Object> getAllHistory(String keyword, Integer status, Integer page) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Customer> customer = customerService.selectAll(1, keyword, status, (page - 1) * 8);
        map.put("customerData", customer);
        double totalPage = Math.ceil(customerService.selectAllCountPage(1, keyword, status) / 8.0);
        map.put("totalPage", totalPage);
        return map;
    }

    @RequestMapping("/login")
    public String login() {
        String account = "text";
        String password = "123456";
        return customerService.login(account, password);
    }

    @RequestMapping("/resetPassword")
    public Boolean resetPassword() {
        Integer id = 1;
        return customerService.resetPassword(id);
    }

    @RequestMapping("/forbid")
    public Boolean forbid() {
        Integer id = 1;
        return customerService.forbidById(id);
    }

    @RequestMapping("/recover")
    public Boolean recover() {
        Integer id = 1;
        return customerService.recoverById(id);
    }

    @RequestMapping("/add")
    public String add() {
        String account = "text110";
        String name = "text110";
        String password = "123456";
        String phone = "12345678901";
        return customerService.insert(new Customer(account, name, password, phone, WAS_USING.getCode(),0));
    }

    @RequestMapping("/update")
    public String update() {
        Integer id = 1;
        String account = customerService.selectById(id).getAccount();
        String name = "44";
        String password = "123456";
        String phone = "110";
        return customerService.update(new Customer(id, account, name, password, phone,1,0));
    }

    @RequestMapping("/getByName")
    public List<Customer> getByName() {
        String name = "text";
        Integer page = 1;
        Integer offset = (page - 1) * 8;
        return customerService.selectByName(name, offset);
    }

    @RequestMapping("/getByStatus")
    public List<Customer> getByStatus() {
        Integer status = 1;
        Integer page = 1;
        Integer offset = (page - 1) * 8;
        return customerService.selectByStatus(status, offset);
    }


}
