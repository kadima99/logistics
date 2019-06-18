package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.InboundOrder;
import com.lnsf.logistics.entity.Orders;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface InboundOrderService {

    List<InboundOrder> selectAll();

    List<InboundOrder> selectByOrderId(Integer id);

    List<InboundOrder> selectByWarehouseId(Integer id, Integer offset);

    Integer countByWarehouseId(Integer id);

    List<InboundOrder> selectById(Integer id);

    List<Integer> getInboundOrderIdByWarehouseId(Integer id,Integer offset);

    Integer countInboundOrderIdByWarehouseId(Integer id);

    Map<String,Object> insert(List<Long> orders, Integer warehouseId) throws ParseException;

    String update(InboundOrder inboundOrder);

    String delete(Integer id);
}
