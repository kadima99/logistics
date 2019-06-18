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
    public List<OutboundOrder> selectById(Integer id) {
        return outboundOrderMapper.selectById(id);
    }

    @Override
    public OutboundOrder selectByOrderIdAnd(Integer id,Integer delMark){
        return outboundOrderMapper.selectByOrderIdAnd(id,delMark);
    }

    @Override
    public List<Integer> getOutboundOrderIdByWarehouseId(Integer id,Integer offset){
        return outboundOrderMapper.getOutboundOrderIdByWarehouseId(id,offset);
    }

    @Override
    public Integer countOutboundOrderIdByWarehouseId(Integer id){
        return outboundOrderMapper.countOutboundOrderIdByWarehouseId(id);
    }

    @Override
    public Boolean insert(List<Long> orders, Integer warehouseId, Integer nextWarehouseId) {

        Integer flag = 0;
        Integer outboundOrderId = 0;
        for (Long orderId : orders) {
            if (flag.equals(0)){
                if (outboundOrderMapper.insert(new OutboundOrder(warehouseId,nextWarehouseId,orderId.intValue(),0))){
                    flag ++;
                }
                List<OutboundOrder> outboundOrders = outboundOrderMapper.selectByOrderId(orders.get(0).intValue());
                outboundOrderId = outboundOrders.get(outboundOrders.size()-1).getOutboundOrderId();
            }
            else if (outboundOrderMapper.insert(new OutboundOrder(outboundOrderId,orderId.intValue(), warehouseId, 0))){
                flag++;
            }
        }
        if (flag.equals(orders.size())) {
            return true;
        } else return false;

    }

    @Override
    public Boolean update(OutboundOrder outboundOrder) {
        if (outboundOrderMapper.update(outboundOrder)){
            return true;
        }else return false;
    }

    @Override
    public Map<String, Object> delete(Integer id) {
        return null;
    }
}
