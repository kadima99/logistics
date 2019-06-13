package com.lnsf.logistics.service.impl;

import com.lnsf.logistics.entity.InboundOrder;
import com.lnsf.logistics.entity.OutboundOrder;
import com.lnsf.logistics.mapper.OutboundOrderMapper;
import com.lnsf.logistics.service.OutboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OutboundOrderServiceImpl implements OutboundOrderService {

    @Autowired
    private OutboundOrderMapper outboundOrderMapper;

    @Override
    public List<OutboundOrder> selectAll() {
        return outboundOrderMapper.selectAll();
    }

    @Override
    public List<OutboundOrder> selectByOrderId(Integer id) {
        return outboundOrderMapper.selectByOrderId(id);
    }

    @Override
    public List<OutboundOrder> selectByWarehouseId(Integer id, Integer offset) {
        return outboundOrderMapper.selectByWarehouseId(id, offset);
    }

    @Override
    public Integer countByWarehouseId(Integer id) {
        return outboundOrderMapper.countByWarehouseId(id);
    }

    @Override
    public OutboundOrder selectById(Integer id) {
        return outboundOrderMapper.selectById(id);
    }

    @Override
    public Map<String, Object> insert(List<Long> orders, Integer warehouseId, Integer nextWarehouseId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer flag = 0;
        for (Long orderId : orders) {
            outboundOrderMapper.insert(new OutboundOrder(warehouseId, nextWarehouseId, orderId.intValue(),0));
            flag++;
        }
        if (flag.equals(orders.size())) {
            map.put("result", true);
        } else map.put("result", "插入失败");
        return map;
    }

    @Override
    public Map<String, Object> update(OutboundOrder inboundOrder) {
        return null;
    }

    @Override
    public Map<String, Object> delete(Integer id) {
        return null;
    }
}
