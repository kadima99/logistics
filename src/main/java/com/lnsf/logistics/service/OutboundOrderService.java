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

    List<OutboundOrder> selectById(Integer id);

    OutboundOrder selectByOrderIdAnd(Integer id,Integer delMark);

    List<Integer> getOutboundOrderIdByWarehouseId(Integer id,Integer offset);

    Integer countOutboundOrderIdByWarehouseId(Integer id);

    Boolean insert(List<Long> orders, Integer warehouseId, Integer nextWarehouseId);

    Boolean update(OutboundOrder inboundOrder);

    Map<String, Object> delete(Integer id);
}
