//package com.lnsf.logistics.controller;
//
//import com.lnsf.logistics.entity.Car;
//import com.lnsf.logistics.entity.NearbyWarehouse;
//import com.lnsf.logistics.service.NearbyWarehouseService;
//import com.lnsf.logistics.service.WarehouseService;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/nearbyWarehouse")
//public class NearbyWarehouseController {
//
//    @Autowired
//    private NearbyWarehouseService nearbyWarehouseService;
//    @Autowired
//    private WarehouseService warehouseService;
//
//    @RequestMapping("/getAll")
//    public String getAll() throws JSONException {
//        Integer page = 1;
//        Integer offset = (page - 1) * 8;
//
//        List<NearbyWarehouse> lnw = nearbyWarehouseService.selectAll();
//        JSONArray array = new JSONArray();
//        for (int i = 0; i < lnw.size(); i++) {
//            JSONObject jsonObject = new JSONObject();
//            String warehouseName = warehouseService.selectById(lnw.get(i).getWarehouseId()).getName();
//            String nearbyWarehouseName = warehouseService.selectById(lnw.get(i).getNearbyWarehouseId()).getName();
//            jsonObject.put("id", lnw.get(i).getId());
//            jsonObject.put("warehouseName", warehouseName);
//            jsonObject.put("nearbyWarehouseName", nearbyWarehouseName);
//            jsonObject.put("distance", lnw.get(i).getDistance());
//            jsonObject.put("delMark", lnw.get(i).getDelMark());
//            array.put(jsonObject);
//        }
//        return array.toString();
//    }
//
//
//    @RequestMapping("/getByWarehouseId")
//    public String getByWarehouseId() throws JSONException {
//        Integer page = 1;
//        Integer offset = (page - 1) * 8;
//        Integer id = 4;
//        Integer level = 1;
//        List<NearbyWarehouse> lnw = nearbyWarehouseService.selectByWarehouseIdAndLevel(id);
//        JSONArray array = new JSONArray();
//        for (int i = 0; i < lnw.size(); i++) {
//            JSONObject jsonObject = new JSONObject();
//            String warehouseName = warehouseService.selectById(lnw.get(i).getWarehouseId()).getName();
//            String nearbyWarehouseName = warehouseService.selectById(lnw.get(i).getNearbyWarehouseId()).getName();
//            jsonObject.put("id", lnw.get(i).getId());
//            jsonObject.put("warehouseName", warehouseName);
//            jsonObject.put("nearbyWarehouseName", nearbyWarehouseName);
//            jsonObject.put("distance", lnw.get(i).getDistance());
//            jsonObject.put("delMark", lnw.get(i).getDelMark());
//            array.put(jsonObject);
//        }
//        return array.toString();
//    }
//
//    @RequestMapping("/getById")
//    public String getById() throws JSONException {
//        Integer id = 1;
//
//        NearbyWarehouse nw = nearbyWarehouseService.selectById(id);
//        JSONObject jsonObject = new JSONObject();
//        String warehouseName = warehouseService.selectById(nw.getWarehouseId()).getName();
//        String nearbyWarehouseName = warehouseService.selectById(nw.getNearbyWarehouseId()).getName();
//        jsonObject.put("id", nw.getId());
//        jsonObject.put("warehouseName", warehouseName);
//        jsonObject.put("nearbyWarehouseName", nearbyWarehouseName);
//        jsonObject.put("distance", nw.getDistance());
//        jsonObject.put("delMark", nw.getDelMark());
//
//        return jsonObject.toString();
//    }
//
//    @RequestMapping("/getByTwoWarehouseId")
//    public String getByWarehouseIdAndOthers() throws JSONException {
//        Integer id = 2;
//        Integer others = 4;
//
//        NearbyWarehouse nw = nearbyWarehouseService.selectByWarehouseIdAndOthers(id,others);
//        JSONObject jsonObject = new JSONObject();
//        String warehouseName = warehouseService.selectById(nw.getWarehouseId()).getName();
//        String nearbyWarehouseName = warehouseService.selectById(nw.getNearbyWarehouseId()).getName();
//        jsonObject.put("id", nw.getId());
//        jsonObject.put("warehouseName", warehouseName);
//        jsonObject.put("nearbyWarehouseName", nearbyWarehouseName);
//        jsonObject.put("distance", nw.getDistance());
//        jsonObject.put("delMark", nw.getDelMark());
//
//        return jsonObject.toString();
//    }
//
//    @RequestMapping("/add")
//    public String add() {
//        Integer id = warehouseService.selectByName("黑龙江一库").getWarehouseId();
//        Integer others = warehouseService.selectByName("黑龙江二库").getWarehouseId();
//        Float distance = 100f;
//        return nearbyWarehouseService.insert(new NearbyWarehouse(id, others, distance, 0));
//
//    }
//
//    @RequestMapping("/update")
//    public String update() {
//        NearbyWarehouse nearbyWarehouse = nearbyWarehouseService.selectById(1);
//        Float distance = 200f;
//        nearbyWarehouse.setDistance(distance);
//        return nearbyWarehouseService.update(nearbyWarehouse);
//    }
//
//    @RequestMapping("/delete")
//    public String delete() {
//        Integer id = 1;
//        return nearbyWarehouseService.delete(id);
//    }
//}
