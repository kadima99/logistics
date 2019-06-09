package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.Orders;

import java.util.List;

public interface OrdersService {

    List<Orders> selectAllOrderByTime(Integer offset);

    List<Orders> selectByLineId(Integer id);

    List<Orders> selectByWarehouseId(Integer id, Integer offset);

    Integer countByWarehouseId(Integer id);

    List<Orders> selectByCustomerId(Integer id, Integer offset);

    Integer countByCustomerId(Integer id);

    List<Orders> selectByWarehouseIdAndEnd(Integer warehouseId, Integer endWarehouseId);

    Orders selectByOrdersId(Integer id);

    Boolean setEndWarehouse(Integer id);

    String insert(Orders orders);

    String update(Orders orders);

    String delete(Integer id);
}
