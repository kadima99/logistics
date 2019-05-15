package com.lnsf.logistics.controller;


import com.lnsf.logistics.entity.User;
import com.lnsf.logistics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getAll")
    public List<User> selectAll() {
        return userService.selectAll();

    }

    @RequestMapping("/getUserByPriority")
    public int test(){
        int id = 5;
        if (userService.deleteById(id)) return  1;
        else return 0;
    }
}
