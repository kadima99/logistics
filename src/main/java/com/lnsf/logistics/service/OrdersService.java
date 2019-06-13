package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.Orders;

import java.util.List;
import java.util.Map;

public interface OrdersService {

    Map<String,Object> getDetails(Integer id);

    List<Orders> selectAllOrderByTime(Integer offset);

    List<Orders> selectByLineId(Integer id);

    List<Orders> selectByWarehouseId(Integer id, Integer offset);

    Integer countByWarehouseId(Integer id);

    List<Orders> selectByCustomerId(Integer id, Integer offset);

    Integer countByCustomerId(Integer id);

    List<Orders> selectByWarehouseIdAndEnd(Integer warehouseId, Integer endWarehouseId);

    List<Orders> selectByStatusAndWarehouseId(Integer warehouseId,Integer status);

    Orders selectByOrdersId(Integer id);

    Boolean setEndWarehouse(Integer id);

    String insert(Orders orders);

    String update(Orders orders);

    String delete(Integer id);
}
