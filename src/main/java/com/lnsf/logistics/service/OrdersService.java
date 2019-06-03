package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.Orders;

import java.util.List;

public interface OrdersService {

    List<Orders> selectAllOrderByTime(Integer offset);

    List<Orders> selectByStatus(Integer status, Integer offset);

    List<Orders> selectByLineId(Integer id, Integer offset);

    List<Orders> selectByWarehouseId(Integer id, Integer offset);

    List<Orders> selectByWarehouseId(Integer id);

    List<Orders> selectByCustomerId(Integer id,Integer offset);

    List<Orders> selectByWarehouseIdAndEnd(Integer warehouseId,Integer endWarehouseId);

    Orders selectByOrdersId(Integer id);

    Integer countByWarehouseIdAndEnd(Integer warehouseId,Integer endWarehouseId);

    String insert(Orders orders);

    String update(Orders orders);

    String delete(Integer id);
}
