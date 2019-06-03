package com.lnsf.logistics.controller;

import com.lnsf.logistics.entity.Car;
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

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;

    @RequestMapping("/getAll")
    public String getAll() throws JSONException {
        int page = 1;
        int offset = (page - 1) * 8;
        // return carService.selectAll(offset);

        List<Car> lc = carService.selectAll(offset);
        JSONObject jsonObject1 = new JSONObject();
        JSONArray array = new JSONArray();
        for (int i = 0; i < lc.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            String name = userService.selectById(lc.get(i).getUserId()).getName();
            try {
                jsonObject.put("carId", lc.get(i).getCarId());
                jsonObject.put("userName", name);
                jsonObject.put("maxWeight", lc.get(i).getMaxWeight());
                jsonObject.put("residueWeight", lc.get(i).getResidueWeight());
                jsonObject.put("status", lc.get(i).getStatus());
                jsonObject.put("delMark", lc.get(i).getDelMark());
                array.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        jsonObject1.put("date",array);
        jsonObject1.put("aa",1);
        return jsonObject1.toString();


    }

    @RequestMapping("/getByStatus")
    public List<Car> getByStatus() {
        int page = 1;
        int offset = (page - 1) * 8;
        int status = 1;
        return carService.selectByStatus(status, offset);
    }

    @RequestMapping("/getById")
    public Car getById() {
        int page = 1;
        int offset = (page - 1) * 8;
        int id = 2;
        return carService.selectById(id);
    }

    @RequestMapping("/add")
    public String add() {
        Integer userId = 3;
        Float maxWeight = 1000f;
        Float residueWeight = 800f;
        Integer status = 1;
        Integer delMark = 0;
        return carService.insert(new Car(userId, maxWeight, residueWeight, status, delMark));
    }

    @RequestMapping("/update")
    public String update() {
        Integer id = 2;
        Integer userId = 3;
        Float maxWeight = 2000f;
        Float residueWeight = 800f;
        Integer status = 1;
        Integer delMark = 0;
        return carService.update(new Car(id, userId, maxWeight, residueWeight, status, delMark));
    }

    @RequestMapping("/delete")
    public String delete() {
        int id = 2;
        return carService.delete(id);
    }

}
