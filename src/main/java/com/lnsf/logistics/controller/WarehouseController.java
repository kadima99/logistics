package com.lnsf.logistics.controller;

import com.lnsf.logistics.entity.User;
import com.lnsf.logistics.entity.Warehouse;
import com.lnsf.logistics.service.LocationsService;
import com.lnsf.logistics.service.WarehouseService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private LocationsService locationsService;

    @RequestMapping("/getAll")
    public Map<String, Object> getAll(Integer page, String keyword, String city, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Warehouse> warehouse = warehouseService.selectAll(keyword, city, (page - 1) * 8);
        map.put("warehouseData", warehouse);
        double totalPage = Math.ceil(warehouseService.selectAllCountPage(keyword, city) / 8.0);
        map.put("totalPage", totalPage);
        return map;
    }

    @RequestMapping("/getAllBrief")
    public String getAllBrief() throws JSONException {
        List<Warehouse> warehouseList = warehouseService.selectAllWarehouseBrief();
        JSONArray array = new JSONArray();
        for (int i = 0; i < warehouseList.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("warehouseId", warehouseList.get(i).getWarehouseId());
            jsonObject.put("name", warehouseList.get(i).getName());
            array.put(jsonObject);
        }
        return array.toString();
    }


    @RequestMapping("/add")
    public Map<String, Object> add(String name, Integer userId, Integer level, Float maxWeight, String address) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (warehouseService.insert(name, userId, level, maxWeight, address).equals("插入成功")) {
            map.put("result", true);
        } else map.put("result", warehouseService.insert(name, userId, level, maxWeight, address));
        return map;

    }

    @RequestMapping("/update")
    public Map<String, Object> update(Integer updateId,String name, Integer userId, Integer level, Float maxWeight, String address, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println(name);
        if (user != null) {
            Warehouse warehouse = warehouseService.selectById(updateId);
            warehouse.setName(name);
            warehouse.setLevel(level);
            warehouse.setMaxWeight(maxWeight);
            warehouse.setAddress(address);
            if (warehouseService.update(warehouse).equals("更新成功")) {
                map.put("result", true);
            } else map.put("result", warehouseService.update(warehouse));
        }
        return map;

    }

    @RequestMapping("/getByArea")
    public List<Warehouse> getByArea() {
        Integer area = 308;
        return warehouseService.selectByArea(area);
    }
//
//    @RequestMapping("/getByLevel")
//    public List<Warehouse> getByLevel() {
//        Integer page = 1;
//        Integer offset = (page - 1) * 8;
//        Integer level = 1;
//        return warehouseService.selectByLevel(level, offset);
//    }
//
//    @RequestMapping("/getByStatus")
//    public List<Warehouse> getByStatus() {
//        Integer page = 1;
//        Integer offset = (page - 1) * 8;
//        Integer status = 1;
//        return warehouseService.selectByStatus(status, offset);
//    }
//
//    @RequestMapping("/getById")
//    public Warehouse getById() {
//        Integer id = 1;
//        return warehouseService.selectById(id);
//    }
//
//    @RequestMapping("/getByUserId")
//    public Warehouse getByUserId() {
//        Integer id = 2;
//        return warehouseService.selectByUserId(id);
//    }
//
//    @RequestMapping("/getByLevelAndArea")
//    public Warehouse getByLevelAndArea() {
//        String area = "北京";
//        Integer level = 1;
//        return warehouseService.selectByAreaAndLevel(area, level);
//    }


}
