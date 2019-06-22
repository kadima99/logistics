package com.lnsf.logistics.service.impl;

import com.lnsf.logistics.entity.InboundOrder;
import com.lnsf.logistics.entity.Orders;
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
    public Integer[] selectByNextWarehouseIdAndDelMark(Integer id,Integer delMark){
        List<Integer> integers = outboundOrderMapper.selectByNextWarehouseIdAndDelMark(id, delMark);
        Integer[] integer = new Integer[integers.size()];
        for (int i =0;i<integers.size();i++){
            integer[i] = integers.get(i);
        }
        return integer;
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
    public Boolean insert(List<Orders> orders, Integer warehouseId, Integer nextWarehouseId) {
        Integer flag = 0;
        Integer outboundOrderId = 0;
        for (Orders order : orders) {
            Integer orderId = order.getOrderId();
            if (flag.equals(0)){
                if (outboundOrderMapper.insert(new OutboundOrder(warehouseId,nextWarehouseId,orderId,0))){
                    flag ++;
                }
                List<OutboundOrder> outboundOrders = outboundOrderMapper.selectByOrderId(orders.get(0).getOrderId());
                outboundOrderId = outboundOrders.get(outboundOrders.size()-1).getOutboundOrderId();
            }
            else if (outboundOrderMapper.insert(new OutboundOrder(outboundOrderId, warehouseId,nextWarehouseId,orderId, 0))){
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
