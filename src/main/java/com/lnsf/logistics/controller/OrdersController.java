package com.lnsf.logistics.controller;


import com.lnsf.logistics.entity.Orders;
import com.lnsf.logistics.service.OrdersService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/getAll")
    public List<Orders> getAll() {
        Integer page = 1;
        Integer offset = (page - 1) * 8;
        return ordersService.selectAllOrderByTime(null);
    }

    @RequestMapping("/selectByLineId")
    public List<Orders> selectByLineId(){
        Integer page = 1;
        Integer offset = (page - 1) * 8;
        Integer lineId = 1;
        return ordersService.selectByLineId(lineId);
    }

        @RequestMapping("/selectByWarehouseId")
    public List<Orders> selectByWarehouseId(){
        Integer page = 1;
        Integer offset = (page - 1) * 8;
        Integer warehouseId = 1;
        return ordersService.selectByWarehouseId(warehouseId,offset);
    }

    @RequestMapping("/getByWarehouseId")
    public List<Orders> getByWarehouseId(){
        Integer page = 1;
        Integer offset = (page - 1) * 8;
        Integer warehouseId = 1;
        return ordersService.selectByWarehouseId(warehouseId,null);
    }

    @RequestMapping("/selectByCustomerId")
    public List<Orders> selectByCustomerId(){
        Integer page = 1;
        Integer offset = (page - 1) * 8;
        Integer customerId = 1;
        return ordersService.selectByCustomerId(customerId,offset);
    }

    @RequestMapping("/selectByWarehouseIdAndEnd")
    public List<Orders> selectByWarehouseIdAndEnd(){
        Integer page = 1;
        Integer offset = (page - 1) * 8;
        Integer warehouseId = 1;
        Integer endWarehouseId =0;
        return ordersService.selectByWarehouseIdAndEnd(warehouseId,endWarehouseId);
    }

    @RequestMapping("/selectByOrdersId")
    public Orders selectByOrdersId(){
        Integer id = 1;
        return ordersService.selectByOrdersId(id);
    }

    @RequestMapping("setEndWarehouse")
    public Boolean setEndWarehouse(){
        Integer id = 1;
        return ordersService.setEndWarehouse(id);
    }

    @RequestMapping("setEndWarehouse1")
    public Boolean setEndWarehouse1(){
        Integer id = 2;
        return ordersService.setEndWarehouse(id);
    }
//    public String insert(Orders orders){
//        return null;
//    }
//
//    public String update(Orders orders){
//        return null;
//    }
//
//    public String delete(Integer id){
//        return null;
//    }


}
