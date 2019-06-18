package com.lnsf.logistics.controller;

import com.lnsf.logistics.entity.Car;
import com.lnsf.logistics.entity.User;
import com.lnsf.logistics.service.CarService;
import com.lnsf.logistics.service.UserService;
import com.lnsf.logistics.service.impl.CarServiceImpl;
import com.lnsf.logistics.service.impl.UserServiceImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lnsf.logistics.Enum.CarStatus.GIFT;
import static com.lnsf.logistics.Enum.CarStatus.NO_BUSY;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;

    @RequestMapping("/getHistoryLocal")
    public Map<String, Object> getHistoryLocal(Integer keyword,Integer page, Integer level, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer offset = (page - 1) * 8;
        if (user != null) {
            List<Car> cars = carService.selectByWarehouseId(keyword,user.getWarehouseId(), level, GIFT.getCode(), offset);
            map.put("carData",cars);
            map.put("totalPage",Math.ceil(carService.countByWarehouseId(keyword,user.getWarehouseId(),level, GIFT.getCode()).doubleValue() / 8.0));
        }
        return map;
    }

    @RequestMapping("/getLocal")
    public Map<String, Object> getLocal(Integer keyword,Integer page, Integer level, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer offset = (page - 1) * 8;
        if (user != null) {
            List<Car> cars = carService.selectByWarehouseId(keyword,user.getWarehouseId(), level, null, offset);
            map.put("carData",cars);
            map.put("totalPage",Math.ceil(carService.countByWarehouseId(keyword,user.getWarehouseId(),level, null).doubleValue() / 8.0));
        }
        return map;
    }

    @RequestMapping("/delete")
    public Map<String, Object> delete(Integer carId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Car car = carService.selectById(carId);
            car.setStatus(GIFT.getCode());
           if (carService.update(car).equals("更新成功")){
               map.put("result",true);
           }else map.put("result",carService.update(car));
        }
        return map;
    }

    @RequestMapping("/add")
    public Map<String, Object> add(Integer level,Float maxWeight, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Car car =new Car(maxWeight,maxWeight, NO_BUSY.getCode(),level,user.getWarehouseId(),user.getWarehouseId());
            if (carService.insert(car).equals("插入成功")){
                map.put("result",true);
            }else map.put("result",carService.insert(car));
        }
        return map;
    }


}
