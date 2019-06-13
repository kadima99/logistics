package com.lnsf.logistics.controller;

import com.lnsf.logistics.entity.InboundOrder;
import com.lnsf.logistics.service.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inboundOrders")

public class InboundOrdersController {

    @Autowired
    private InboundOrderService inboundOrderService;

    @RequestMapping("add")
    public Map<String,Object> add() throws ParseException {
        Integer warehouseId = 2;
        List<Long> list = new ArrayList<>();
        list.add(100L);
        list.add(200L);
        return inboundOrderService.insert(list,warehouseId);
    }
}
