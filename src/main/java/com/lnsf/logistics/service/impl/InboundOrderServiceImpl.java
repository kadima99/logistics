package com.lnsf.logistics.service.impl;

import com.lnsf.logistics.entity.InboundOrder;
import com.lnsf.logistics.entity.Orders;
import com.lnsf.logistics.mapper.InboundOrderMapper;
import com.lnsf.logistics.service.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class InboundOrderServiceImpl implements InboundOrderService {

    @Autowired
    private InboundOrderMapper inboundOrderMapper;

    @Override
    public List<InboundOrder> selectAll() {
        return inboundOrderMapper.selectAll();
    }

    @Override
    public List<InboundOrder> selectByOrderId(Integer id) {
        return inboundOrderMapper.selectByOrderId(id);
    }

    @Override
    public List<InboundOrder> selectByWarehouseId(Integer id, Integer offset) {
        return inboundOrderMapper.selectByWarehouseId(id, offset);
    }

    @Override
    public Integer countByWarehouseId(Integer id) {
        return inboundOrderMapper.countByWarehouseId(id);
    }

    @Override
    public InboundOrder selectById(Integer id) {
        return inboundOrderMapper.selectById(id);
    }

    @Override
    public Map<String, Object> insert(List<Long> orders, Integer warehouseId){
        Map<String, Object> map = new HashMap<String, Object>();
        Integer flag = 0;
        for (Long orderId : orders) {
            inboundOrderMapper.insert(new InboundOrder(orderId.intValue(), warehouseId, 0));
            flag++;
        }
        if (flag.equals(orders.size())) {
            map.put("result", true);
        } else map.put("result", "插入失败");
        return map;
    }

    @Override
    public String update(InboundOrder inboundOrder) {
        return null;
    }

    @Override
    public String delete(Integer id) {
        return null;
    }
}
