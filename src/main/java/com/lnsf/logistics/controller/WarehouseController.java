package com.lnsf.logistics.controller;

import com.lnsf.logistics.entity.Warehouse;
import com.lnsf.logistics.service.WarehouseService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

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
        Warehouse warehouse = new Warehouse(name, address, userId, address, level, maxWeight, 0f, 0);
        if (warehouseService.insert(warehouse).equals("插入成功")) {
            map.put("result", true);
        } else map.put("result", warehouseService.insert(warehouse));
        return map;

    }

    @RequestMapping("/update")
    public Map<String, Object> update(String name, Integer userId, Integer level, Float maxWeight, String address){
        Map<String, Object> map = new HashMap<String, Object>();
        Warehouse warehouse = warehouseService.selectById(userId);
        warehouse.setName(name);
        warehouse.setLevel(level);
        warehouse.setMaxWeight(maxWeight);
        warehouse.setAddress(address);
        if (warehouseService.update(warehouse).equals("更新成功")){
            map.put("result",true);
        }else map.put("result",warehouseService.update(warehouse));
        return map;

    }

    //    @RequestMapping("/getByArea")
//    public List<Warehouse> getByArea() {
//        Integer page = 1;
//        Integer offset = (page - 1) * 8;
//        String area = "北京";
//        return warehouseService.selectByArea(area, offset);
//    }
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