package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.HandoverOrder;

import java.util.List;
import java.util.Map;

public interface HandoverOrderService {

    List<HandoverOrder> selectAll();

    List<HandoverOrder> selectByHandoverOrderId(Integer id, Integer offset);

    List<HandoverOrder> selectByUserId(Integer id, Integer offset);

    HandoverOrder selectByOutboundId(Integer id);

    Map<String, Object> insert(Integer userId, Integer[] outboundIds);

    Map<String, Object> update(HandoverOrder handoverOrder);

    Map<String, Object> delete(HandoverOrder hangoverOrder);
}
