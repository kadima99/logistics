package com.lnsf.logistics.controller;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.lnsf.logistics.entity.User;
import com.lnsf.logistics.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getAll", method = GET)
    public String getAll(@RequestParam("page") String rePage , HttpServletRequest req ,
                         HttpServletResponse rep ) throws JSONException {
//        ,@RequestParam("keyword") String reKeyword,@RequestParam("priority") Integer rePriority,@RequestParam("warehouseValue") String reWarehouseValue
        int page = Integer.parseInt(rePage);
        int offset = (page - 1) * 8;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userDate", userService.selectAll(offset));

        while (userService.selectAll(offset).size() != 0) {
            System.out.println("stop");
            page++;
            offset = (page - 1) * 8;
        }
        int totalPage = page;
        jsonObject.put("totalPage", totalPage);
        return jsonObject.toString();
    }

//    @RequestMapping("/getByPriority")
//    public List<User> getUserByPriority() {
//        int page = 1;
//        int offset = (page - 1) * 8;
//        int priority = 2;
//        return userService.selectByPriority(priority, offset);
//    }
//
//    @RequestMapping("/getByStatus")
//    public List<User> getUserByStatus() {
//        int page = 1;
//        int offset = (page - 1) * 8;
//        int status = 1;
//        return userService.selectByStatus(status, offset);
//    }
//
//    @RequestMapping("/getById")
//    public User getUserById(@RequestBody JSONObject requestJson) throws JSONException {
//        int id = Integer.parseInt(requestJson.getString("id"));
//        return userService.selectById(id);
//    }
//
//    @RequestMapping("/getByName")
//    public List<User> getUserByName() {
//        int page = 1;
//        int offset = (page - 1) * 8;
//        String name = "1234";
//        return userService.selectByName(name, offset);
//    }
//@RequestParam(value = "username") String account,@RequestParam(value = "password") String password

    @RequestMapping(value = "/login",method = POST)
    public String login(String username,String password,HttpServletRequest request)  throws JSONException {
        System.out.println("1");
        System.out.println(username+password);
//        String account = requestJson.getString("username");
//        String password = requestJson.getString("password");
//        System.out.println(account);
//        System.out.println(password);
//        if (userService.login(account, password).equals("登录成功")) {
//            String result = userService.selectByAccount(account).getName().toString().concat(userService.selectByAccount(account).getPriority().toString());
//            return result;
//        } else return userService.login(account, password);
        return "1";
    }

    @RequestMapping("/getByWarehouseId")
    public List<User> getUserByWarehouseId() {
        int page = 1;
        int offset = (page - 1) * 8;
        int warehouseId = 2;
        return userService.selectByWarehouseId(warehouseId, offset);

    }

    @RequestMapping("/resetPassword")
    public Boolean resetPassword() {
        int id = 5;
        User user = userService.selectById(id);
        user.setPassword("123456");
        System.out.println(user);
        if (userService.update(user).equals("修改成功")) {
            return true;
        } else return false;

    }

    @RequestMapping("/recover")
    public Boolean recover() {
        int id = 7;
        return userService.updateDelMarkById(id);
    }

    @RequestMapping("/add")
    public String add() {
        String account = "text";
        String name = "mytext";
        String password = "text";
        String phone = "10086";
        Integer priority = 1;
        Integer warehouseId = 2;
        Integer status = 3;
        Integer delMark = 1;
        return userService.insert(new User(account, password, name, phone, priority, warehouseId, status, delMark));
    }

    @RequestMapping("/update")
    public String update() {
        Integer id = 19;
        String account = userService.selectById(id).getAccount();
        String name = "mytext2";
        String password = "text";
        String phone = "10086";
        Integer priority = 1;
        Integer warehouseId = 2;
        Integer status = 3;
        Integer delMark = 0;
        return userService.update(new User(id, account, password, name, phone, priority, warehouseId, status, delMark));
    }

    @RequestMapping("/delete")
    public String delete() {
        int id = 5;
        return userService.deleteById(id);
    }

}
