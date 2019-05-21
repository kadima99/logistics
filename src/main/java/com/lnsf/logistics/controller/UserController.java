package com.lnsf.logistics.controller;


import com.lnsf.logistics.entity.User;
import com.lnsf.logistics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getAll")
    public List<User> selectAll() {
        int page = 2;
        int offset = (page - 1) * 8;
        return userService.selectAll(offset);
    }

    @RequestMapping("/getByPriority")
    public List<User> getUserByPriority() {
        int page = 1;
        int offset = (page - 1) * 8;
        int priority = 2;
        return userService.selectByPriority(priority, offset);
    }

    @RequestMapping("/getByStatus")
    public List<User> getUserByStatus() {
        int page = 1;
        int offset = (page - 1) * 8;
        int status = 1;
        return userService.selectByStatus(status, offset);
    }

    @RequestMapping("/getById")
    public User getUserById() {
        int id = 2;
        return userService.selectById(id);
    }

    @RequestMapping("/getByName")
    public List<User> getUserByName() {
        int page = 1;
        int offset = (page - 1) * 8;
        String name = "1234";
        return userService.selectByName(name, offset);
    }

    @RequestMapping("/login")
    public String login() {
        String account = "admin";
        String password = "admin";
        User user = userService.selectByAccountAndPassword(account, password);
        return user.getName();
    }

    @RequestMapping("/getByWarehouseId")
    public List<User> getUserByWarehouseId(){
        int page = 1;
        int offset = (page - 1) * 8;
        int warehouseId = 2;
        return userService.selectByWarehouseId(warehouseId,offset);

    }

    @RequestMapping("/checkRole")
    public Integer checkRole(){
        int id = 3;
        return userService.selectById(id).getPriority();
    }

    @RequestMapping("/resetPassword")
    public Boolean resetPassword(){
        int id = 5;
        User user = userService.selectById(id);
        user.setPassword("123456");
        System.out.println(user);
        if (userService.update(user).equals("修改成功")){
            return true;
        }else return false;

    }

    @RequestMapping("/recover")
    public Boolean recover(){
        int id = 7 ;
        User user = userService.selectById(id);
        user.setDelMark(0);
        return userService.updateDelMarkById(id);
    }

    @RequestMapping("/add")
    public String add(){
        String account = "text";
        String name = "mytext";
        String password = "text";
        String phone = "10086";
        Integer priority = 1;
        Integer warehouseId = 2;
        Integer status = 3;
        Integer delMark = 1;
        return userService.insert(new User(account,password,name,phone,priority,warehouseId,status,delMark));
    }

    @RequestMapping("/update")
    public String update(){
        Integer id = 20;
        String name = "mytext2";
        String password = "text";
        String phone = "10086";
        String account = "text";
        Integer priority = 1;
        Integer warehouseId = 2;
        Integer status = 3;
        Integer delMark = 0;
        return userService.update(new User(id,account,password,name,phone,priority,warehouseId,status,delMark));
    }

    @RequestMapping("/delete")
    public String delete(){
        int id = 5;
        return userService.deleteById(id);
    }

}
