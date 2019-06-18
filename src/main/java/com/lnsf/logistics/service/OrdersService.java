package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.Orders;
import org.apache.ibatis.annotations.Select;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface OrdersService {

    Map<String, Object> getDetails(Integer id);

    List<Orders> selectAllOrderByTime(Integer offset);

    List<Orders> selectByLineId(Integer id);

    List<Orders> selectByWarehouseId(Integer keyword,Integer id, Integer status, Integer offset);

    Integer countByWarehouseId(Integer keyword,Integer id, Integer status);

    List<Orders> selectByCustomerId(Integer id, Integer status, Integer offset);

    Integer countByCustomerId(Integer id, Integer status);

    List<Orders> selectByReceiverPhone(String phone, Integer status, Integer offset);

    Integer countByReceiverPhone(String phone, Integer status);

    List<Orders> getDelivery(Integer keyword,Integer warehouseId);

    Integer countByGetDelivery(Integer keyword,Integer warehouseId);

    List<Orders> selectByWarehouseIdAndEnd(Integer warehouseId, Integer endWarehouseId);

    List<Orders> selectByStatusAndWarehouseId(Integer warehouseId, Integer status);

    List<Orders> selectByUserIdAndOrderStatus(Integer keyword, Integer id, Integer status, Integer offset);

    Integer countByUserIdAndOrderStatus(Integer keyword, Integer id, Integer status);

    Map<String, Object> outboundConfirm(Integer outboundOrderId);

    Map<String, Object> driverFinish(Integer handoverOrderId) throws ParseException;

    Map<String, Object> finish(Integer orderId);

    Boolean checkArea(String area);

    Orders selectByOrdersId(Integer id);

    Boolean setEndWarehouse(Integer id);

    String insert(Orders orders);

    String update(Orders orders);

    String delete(Integer id);
}
