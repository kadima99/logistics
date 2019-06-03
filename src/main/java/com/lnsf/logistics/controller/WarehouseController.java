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

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @RequestMapping("/getAll")
    public List<Warehouse> getAll() {
        Integer page = 1;
        Integer offset = (page - 1) * 8;
        return warehouseService.selectAll(offset);
    }

    @RequestMapping("/getAllBrief")
    public String getAllBrief() throws JSONException {
        List<Warehouse> warehouseList = warehouseService.selectAllWarehouse();
        JSONObject rep = new JSONObject();
        JSONArray array = new JSONArray();
        for (int i = 0; i < warehouseList.size(); i++) {
            JSONObject jsonObject = new JSONObject();
                jsonObject.put("warehouseId", warehouseList.get(i).getWarehouseId());
                jsonObject.put("name",warehouseList.get(i).getName());
                array.put(jsonObject);
        }
        rep.put("warehouseData",array);
        return rep.toString();
    }

    @RequestMapping("/getByArea")
    public List<Warehouse> getByArea() {
        Integer page = 1;
        Integer offset = (page - 1) * 8;
        String area = "北京";
        return warehouseService.selectByArea(area, offset);
    }

    @RequestMapping("/getByLevel")
    public List<Warehouse> getByLevel() {
        Integer page = 1;
        Integer offset = (page - 1) * 8;
        Integer level = 1;
        return warehouseService.selectByLevel(level, offset);
    }

    @RequestMapping("/getByStatus")
    public List<Warehouse> getByStatus() {
        Integer page = 1;
        Integer offset = (page - 1) * 8;
        Integer status = 1;
        return warehouseService.selectByStatus(status, offset);
    }

    @RequestMapping("/getById")
    public Warehouse getById() {
        Integer id = 1;
        return warehouseService.selectById(id);
    }

    @RequestMapping("/getByUserId")
    public Warehouse getByUserId() {
        Integer id = 2;
        return warehouseService.selectByUserId(id);
    }

    @RequestMapping("/getByLevelAndArea")
    public Warehouse getByLevelAndArea() {
        String area = "北京";
        Integer level = 1;
        return warehouseService.selectByAreaAndLevel(area, level);
    }

    @RequestMapping("/add")
    public String add() {
        String name = "黑龙江一库";
        String address = "黑龙江不知道在那里";
        Integer userId = 4;
        String area = "东北";
        Integer level = 5;
        Float maxWeight = 1000.0f;
        Float residueWeight = 0f;
        Integer status = 0;
        return warehouseService.insert(new Warehouse(name,address,userId,area,level,maxWeight,residueWeight,status));

    }

    @RequestMapping("/update")
    public String update(){
        Integer id = 3;
        Warehouse warehouse = warehouseService.selectById(id);
        String name = "黑龙江二库";
        warehouse.setName(name);
        return warehouseService.update(warehouse);

    }

}
