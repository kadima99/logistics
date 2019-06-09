package com.lnsf.logistics.service.impl;


import com.lnsf.logistics.entity.Orders;
import com.lnsf.logistics.entity.Warehouse;
import com.lnsf.logistics.mapper.CustomerMapper;
import com.lnsf.logistics.mapper.OrdersMapper;
import com.lnsf.logistics.service.CustomerService;
import com.lnsf.logistics.service.LocationsService;
import com.lnsf.logistics.service.OrdersService;
import com.lnsf.logistics.service.WarehouseService;
import org.apache.catalina.startup.WebAnnotationSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static com.lnsf.logistics.Enum.OrdersStatus.IS_DELIVERING;
import static com.lnsf.logistics.Enum.OrdersStatus.WAIT_FOR;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private LocationsService locationsService;
    @Autowired
    private WarehouseService warehouseService;

    @Override
    public List<Orders> selectAllOrderByTime(Integer offset) {
        String sql = "SELECT * FROM orders where 1=1 ORDER BY create_date ";
        if (offset != null) {
            sql += " LIMIT " + offset + ",8";
        }
        return ordersMapper.selectAllOrderByTime(sql);
    }

    @Override
    public List<Orders> selectByLineId(Integer id) {
        return ordersMapper.selectByLineId(id);
    }

    @Override
    public List<Orders> selectByWarehouseId(Integer id, Integer offset) {
        String sql = "SELECT * FROM orders where warehouse_id = " + id;
        sql += " ORDER BY create_date ";
        if (offset != null) {
            sql += " LIMIT " + offset + ",8";
        }
        return ordersMapper.selectAllOrderByTime(sql);
    }

    @Override
    public Integer countByWarehouseId(Integer id) {
        String sql = "SELECT count(order_id) FROM orders where 1=1 ORDER BY create_date ";
        return ordersMapper.countByWarehouseId(sql);
    }

    @Override
    public List<Orders> selectByCustomerId(Integer id, Integer offset) {
        return ordersMapper.selectByCustomerId(id, offset);
    }

    @Override
    public Integer countByCustomerId(Integer id) {
        return ordersMapper.countByCustomerId(id);
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
    public Boolean setEndWarehouse(Integer id) {
        Orders orders = selectByOrdersId(id);
        Map<String, Object> map = locationsService.selectLocationsByAddress(orders.getReceiverAddress());
        String area = map.get("province").toString() + map.get("city").toString();
        if (warehouseService.selectByAreaAndLevel(area, 3) != null) {
            if (warehouseService.selectByAreaAndLevel(area, 3).getWarehouseId().equals(orders.getWarehouseId())) {
                area += map.get("county").toString();
                List<Warehouse> warehouseList = warehouseService.selectByArea(area);
                if (warehouseList != null) {
                    orders.setEndWarehouseId(warehouseList.get(0).getWarehouseId());
                }
            } else orders.setEndWarehouseId(warehouseService.selectByAreaAndLevel(area, 3).getWarehouseId());

        }else return false;
        if (ordersMapper.update(orders)) {
            return true;
        } else return false;
    }

    @Override
    public String insert(Orders orders) {
        if (customerService.selectById(orders.getCustomerId()) == null) {
            return "无此用户";
        } else if (setEndWarehouse(orders.getOrderId())) {
            if (ordersMapper.insert(orders)) {
                return "插入成功";
            } else return "插入失败";
        } else return "不支持该地区的配送！";

    }

    @Override
    public String update(Orders orders) {
        if (orders.getStatus() != IS_DELIVERING.getCode()) {
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
