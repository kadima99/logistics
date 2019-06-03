package com.lnsf.logistics.service.impl;


import com.lnsf.logistics.entity.Orders;
import com.lnsf.logistics.mapper.CustomerMapper;
import com.lnsf.logistics.mapper.OrdersMapper;
import com.lnsf.logistics.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.lnsf.logistics.Enum.OrdersStatus.WAIT_FOR;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Orders> selectAllOrderByTime(Integer offset) {
        return ordersMapper.selectAllOrderByTime(offset);
    }

    @Override
    public List<Orders> selectByStatus(Integer status, Integer offset) {
        return ordersMapper.selectByStatus(status, offset);
    }

    @Override
    public List<Orders> selectByLineId(Integer id, Integer offset) {
        return ordersMapper.selectByLineId(id, offset);
    }

    @Override
    public List<Orders> selectByWarehouseId(Integer id, Integer offset) {
        return ordersMapper.selectByWarehouseId(id, offset);
    }

    @Override
    public List<Orders> selectByWarehouseId(Integer id) {
        return ordersMapper.selectAllByWarehouseId(id);
    }

    @Override
    public List<Orders> selectByCustomerId(Integer id, Integer offset) {
        return ordersMapper.selectByCustomerId(id, offset);
    }

    @Override
    public List<Orders> selectByWarehouseIdAndEnd(Integer warehouseId, Integer endWarehouseId) {
        return ordersMapper.selectByWarehouseIdAndEnd(warehouseId, endWarehouseId);
    }

    @Override
    public Orders selectByOrdersId(Integer id) {
        return ordersMapper.selectByOrdersId(id);
    }

    @Override
    public Integer countByWarehouseIdAndEnd(Integer warehouseId, Integer endWarehouseId) {
        return ordersMapper.countByWarehouseIdAndEnd(warehouseId, endWarehouseId);
    }


    @Override
    public String insert(Orders orders) {
        if (customerMapper.selectById(orders.getCustomerId()) == null) {
            return "无此用户";
        } else if (ordersMapper.insert(orders)) {
            return "插入成功";
        } else return "插入失败";
    }


    @Override
    public String update(Orders orders) {
        if (orders.getStatus() != WAIT_FOR.getCode()) {
            return "订单正在配送，无法修改信息！";
        } else if (ordersMapper.update(orders)) {
            return "更新成功";
        } else return "更新失败";
    }

    @Override
    public String delete(Integer id) {
        if (selectByOrdersId(id).getStatus().equals(WAIT_FOR.getCode())) {
            if (ordersMapper.delete(id)) {
                return "删除成功";
            } else return "删除失败";
        } else return "订单正在配送，无法取消订单！";
    }
}
