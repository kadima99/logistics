package com.lnsf.logistics.controller;

import com.lnsf.logistics.entity.Customer;
import com.lnsf.logistics.entity.Orders;
import com.lnsf.logistics.entity.User;
import com.lnsf.logistics.service.CustomerService;

import com.lnsf.logistics.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lnsf.logistics.Enum.CustomerStatus.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/getAll")
    public Map<String, Object> selectAll(String keyword, Integer status, Integer page, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<String, Object> map = new HashMap<String, Object>();
        if (session.getAttribute("user") != null) {
            List<Customer> customer = customerService.selectAll(0, keyword, status, (page - 1) * 8);
            map.put("customerData", customer);
            double totalPage = Math.ceil(customerService.selectAllCountPage(0, keyword, status) / 8.0);
            map.put("totalPage", totalPage);
        }
        return map;
    }

    @RequestMapping("/getAllHistory")
    public Map<String, Object> getAllHistory(String keyword, Integer status, Integer page, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<String, Object> map = new HashMap<String, Object>();
        if (session.getAttribute("user") != null) {
            List<Customer> customer = customerService.selectAll(1, keyword, status, (page - 1) * 8);
            map.put("customerData", customer);
            double totalPage = Math.ceil(customerService.selectAllCountPage(1, keyword, status) / 8.0);
            map.put("totalPage", totalPage);
        }
        return map;
    }

    @RequestMapping("/login")
    public Map<String, Object> login(String account, String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<String, Object> map = new HashMap<String, Object>();
        if (customerService.login(account, password).equals("登陆成功")) {
            Customer customer = customerService.selectByAccount(account);
            map.put("customerName", account);
            session.setAttribute("customer", customer);
        } else map.put("result", customerService.login(account, password));
        return map;
    }

    @RequestMapping("/logout")
    public Map<String, Object> logout(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        if (session.getAttribute("customer") != null) {
            System.out.println(session.getAttribute("customer"));
            session.removeAttribute("customer");
            map.put("result", true);
        } else map.put("result", false);
        return map;
    }

    @RequestMapping("/logoff")
    public Map<String, Object> logoff(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            customer.setDelMark(1);
            if (customerService.delete(customer.getCustomerId()).equals("删除成功")) ;
            session.removeAttribute("customer");
            map.put("result", true);
        } else map.put("result", false);
        return map;
    }

    @RequestMapping("/updatePassword")
    public Map<String, Object> updatePassword(Integer customerId, String currentPassword, String password, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            if (customer.getPassword().equals(currentPassword)) {
                customer.setPassword(password);
                if (customerService.update(customer).equals("修改成功")) {
                    session.setAttribute("customer", customer);
                    map.put("result", true);
                } else map.put("result", customerService.update(customer));
            } else map.put("result", "原密码错误，请重新输入");
        }
        return map;
    }

    @RequestMapping("/forbid")
    public Map<String, Object> forbid(@RequestParam("customerId") List<Long> customerId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        if (loginUser != null) {
            return customerService.forbidById(customerId);
        } else {
            map.put("result", "session过期");
            return map;
        }
    }

    @RequestMapping("/recover")
    public Map<String, Object> recover(@RequestParam("customerId") List<Long> customerId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        if (loginUser != null) {
            return customerService.recoverById(customerId);
        } else {
            map.put("result", "session过期");
            return map;
        }
    }

    @RequestMapping("/register")
    public Map<String, Object> add(String account, String name, String password, String phone, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        Customer customer = new Customer(account, name, password, phone, IS_USING.getCode(), 0);
        if (customerService.insert(customer).equals("插入成功")) {
            map.put("result", true);
        } else map.put("result", customerService.insert(customer));
        return map;
    }

    @RequestMapping("/update")
    public Map<String, Object> update(Integer customerId, String name, String phone, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {

            customer.setName(name);
            customer.setPhone(phone);

            if (customerService.update(customer).equals("修改成功")) {
                session.setAttribute("customer", customer);

                map.put("result", true);
            } else map.put("result", "修改失败");
        }
        return map;
    }

    @RequestMapping("/getSendOrder")
    public Map<String, Object> getSendOrder(Integer page, Integer status, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            List<Orders> orders = ordersService.selectByCustomerId(customer.getCustomerId(), status, (page - 1) * 8);
            map.put("orderData", orders);
            double totalPage = Math.ceil(ordersService.countByCustomerId(customer.getCustomerId(), status).doubleValue() / 8.0);
            map.put("totalPage", totalPage);
        }
        return map;
    }

    @RequestMapping("/getReceiveOrder")
    public Map<String, Object> getReceiveOrder(Integer page, Integer status, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            List<Orders> orders = ordersService.selectByReceiverPhone(customer.getPhone(), status, (page - 1) * 8);
            map.put("orderData", orders);
            double totalPage = Math.ceil(ordersService.countByReceiverPhone(customer.getPhone(), status).doubleValue() / 8.0);
            map.put("totalPage", totalPage);
        }
        return map;
    }

    @RequestMapping("getAccount")
    public Map<String, Object> getAccount( HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            map.put("customerId",customer.getCustomerId());
            map.put("account",customer.getAccount());
            map.put("name",customer.getName());
            map.put("phone",customer.getPhone());
        }
        return map;
    }

//    @RequestMapping("/getByName")
//    public List<Customer> getByName() {
//        String name = "text";
//        Integer page = 1;
//        Integer offset = (page - 1) * 8;
//        return customerService.selectByName(name, offset);
//    }
//
//    @RequestMapping("/getByStatus")
//    public List<Customer> getByStatus() {
//        Integer status = 1;
//        Integer page = 1;
//        Integer offset = (page - 1) * 8;
//        return customerService.selectByStatus(status, offset);
//    }


}
