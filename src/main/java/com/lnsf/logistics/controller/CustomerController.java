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
}
