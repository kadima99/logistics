package com.lnsf.logistics.controller;

import com.lnsf.logistics.entity.Customer;
import com.lnsf.logistics.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @RequestMapping("/all")
    List<Customer> selectAll(){
        return customerService.selectAll();
    }

    @RequestMapping("/sqlTest")
    public int test(){
        Customer customer = new Customer();
        customer.setCustomerId(3);
        customer.setAccount("c01");
        customer.setDelMark(1);
        customer.setName("c01");
        customer.setPassword("123456");
        customer.setPhone("12345678901");
        if (customerService.update(customer)) return  1;
        else return 0;
    }
}
