package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.OutboundOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface OutboundOrderService {

    List<OutboundOrder> selectAll();

    List<OutboundOrder> selectByOrderId(Integer id);

    List<OutboundOrder> selectByWarehouseId(Integer id, Integer offset);

    Integer countByWarehouseId(Integer id);

    OutboundOrder selectById(Integer id);

    Map<String, Object> insert(List<Long> orders, Integer warehouseId, Integer nextWarehouseId);

    Map<String, Object> update(OutboundOrder inboundOrder);

    Map<String, Object> delete(Integer id);
}
