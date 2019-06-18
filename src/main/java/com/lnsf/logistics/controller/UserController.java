package com.lnsf.logistics.controller;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.lnsf.logistics.entity.User;
import com.lnsf.logistics.service.UserService;
import com.lnsf.logistics.service.WarehouseService;
import org.json.JSONArray;
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
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lnsf.logistics.Enum.UserPriority.DELIVER;
import static com.lnsf.logistics.Enum.UserPriority.DRIVER;
import static com.lnsf.logistics.Enum.UserStatus.NO_BUSY;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private WarehouseService warehouseService;

    @RequestMapping(value = "/getAll")
    public Map<String, Object> getAll(Integer page, String keyword, Integer priority, Integer warehouseId, HttpServletRequest request) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            List<User> user = userService.selectAll(0, keyword, priority, warehouseId, (page - 1) * 8);
            map.put("userData", user);
            double totalPage = Math.ceil(userService.selectAllCountPage(0, keyword, priority, warehouseId).doubleValue() / 8.0);
            map.put("totalPage", totalPage);
        }
        return map;
    }


    @RequestMapping(value = "/getAllHistory")
    public Map<String, Object> getAllHistory(Integer page, String keyword, Integer priority, Integer warehouseId, HttpServletRequest request) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            List<User> users = userService.selectAll(1, keyword, priority, warehouseId, (page - 1) * 8);
            map.put("userData", users);
            double totalPage = Math.ceil(userService.selectAllCountPage(1, keyword, priority, warehouseId).doubleValue() / 8.0);
            map.put("totalPage", totalPage);
        }
        return map;
    }

    @RequestMapping(value = "/login")
    public Map<String, Object> login(String account, String password, HttpServletRequest request) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userService.login(account, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            map.put("username", user.getName());
            map.put("auth", user.getPriority());
        }
        return map;
//            String result = userService.selectByAccount(account).getName().concat(userService.selectByAccount(account).getPriority().toString());

    }

    @RequestMapping(value = "/logout")
    public Map<String, Object> logout(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
            map.put("result", true);
        } else map.put("result", false);

        return map;
    }

    @RequestMapping(value = "/resetPassword", method = POST)
    public Map<String, Object> resetPassword(@RequestParam("userId") List<Long> userId, HttpServletRequest request) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        if (loginUser != null) {
            return userService.resetPassword(userId);
        } else {
            map.put("result", "session过期");
            return map;
        }
    }

    @RequestMapping("/getAccount")
    public User getAccount(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        return user;
    }

    @RequestMapping("/updatePassword")
    public Map<String, Object> updatePassword(Integer userId, String currentPassword, String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<String, Object> map = new HashMap<String, Object>();
        User loginUser = (User) session.getAttribute("user");
        if (loginUser != null) {
            if (currentPassword.equals(loginUser.getPassword())) {
                loginUser.setPassword(password);
                if (userService.update(loginUser).equals("修改成功")) {
                    map.put("result", true);
                    session.setAttribute("user", loginUser);
                } else map.put("result", userService.update(loginUser));
            } else {
                map.put("result", "原有密码不正确！");
            }
        }

        return map;
    }

    @RequestMapping("/recover")
    public Map<String, Object> recover(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<String, Object> map = new HashMap<String, Object>();
        if (session != null) {
            User loginUser = (User) session.getAttribute("user");
            Integer id = loginUser.getUserId();
            if (userService.updateDelMarkById(id)) {
                map.put("result", true);
            }
        } else map.put("result", null);
        return map;
    }

    @RequestMapping("/add")
    public Map<String, Object> add(String name, String phone, Integer warehouseId, Integer role, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<String, Object> map = new HashMap<String, Object>();
        User loginUser = (User) session.getAttribute("user");
        if (loginUser != null) {
            //account生成规则 仓库名 + 该仓库第几个员工
            Integer count = userService.selectByWarehouseIdCountPage(warehouseId) + 1;
            String account = new DecimalFormat("00").format(warehouseId) + new DecimalFormat("00").format(count);
            User user = new User(account, "123456", name, phone, role, warehouseId, NO_BUSY.getCode(), 0);
            if (userService.insert(user).equals("插入成功")) {
                map.put("result", true);
            } else map.put("result", userService.insert(user));

        }
        return map;
    }

    @RequestMapping("/update")
    public Map<String, Object> update(Integer userId, String name, String phone, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<String, Object> map = new HashMap<String, Object>();
        User loginUser = (User) session.getAttribute("user");
        if (loginUser != null) {
            loginUser.setName(name);
            loginUser.setPhone(phone);
            if (userService.update(loginUser).equals("修改成功")) {
                map.put("result", true);
            } else map.put("result", userService.update(loginUser));
        }
        return map;
    }

    @RequestMapping("/delete")
    public Map<String, Object> delete(@RequestParam("userId") List<Long> userId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        return userService.deleteById(userId);
    }

    @RequestMapping("/getDeliver")
    public List<User> getDeliver(HttpServletRequest request){
        List<User> users = new ArrayList<User>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null){
            users = userService.selectByWarehouseIdAndPriority(user.getWarehouseId(),DELIVER.getCode());
        }
        return users;
    }

    @RequestMapping("/getDriver")
    public List<User> getDriver(HttpServletRequest request){
        List<User> users = new ArrayList<User>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null){
            users = userService.selectByWarehouseIdAndPriority(user.getWarehouseId(),DRIVER.getCode());
        }
        return users;
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


}
