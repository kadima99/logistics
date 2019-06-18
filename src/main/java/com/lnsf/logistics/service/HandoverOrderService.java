package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.HandoverOrder;

import java.util.List;
import java.util.Map;

public interface HandoverOrderService {

    List<HandoverOrder> selectAll();

    List<HandoverOrder> selectByHandoverOrderId(Integer id);

    List<HandoverOrder> selectByUserIdAndStatus(Integer keyword,Integer userId, Integer status);

    HandoverOrder selectByOutboundId(Integer id);

    List<Integer> getHandoverOrderIdByUserId(Integer id,Integer offset);

    Integer countHandoverOrderIdByUserId(Integer id);

    Map<String, Object> inboundInsert(Integer userId, Integer[] outboundIds,Integer linId);

    Map<String, Object> outboundInsert(Integer userId, Integer[] outboundIds,Integer warehouseId);

    Map<String, Object> update(HandoverOrder handoverOrder);

    Map<String, Object> delete(HandoverOrder hangoverOrder);
}
