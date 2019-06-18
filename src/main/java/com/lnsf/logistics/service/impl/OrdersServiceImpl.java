package com.lnsf.logistics.service.impl;


import com.lnsf.logistics.Enum.UserPriority;
import com.lnsf.logistics.entity.*;
import com.lnsf.logistics.mapper.CustomerMapper;
import com.lnsf.logistics.mapper.OrdersMapper;
import com.lnsf.logistics.service.*;
import org.apache.catalina.startup.WebAnnotationSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lnsf.logistics.Enum.OrdersStatus.*;
import static com.lnsf.logistics.Enum.UserPriority.DELIVER;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private InboundOrderService inboundOrderService;
    @Autowired
    private OutboundOrderService outboundOrderService;
    @Autowired
    private HandoverOrderService handoverOrderService;
    @Autowired
    private LocationsService locationsService;
    @Autowired
    private WarehouseService warehouseService;


    @Override
    public Map<String, Object> getDetails(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        if (selectByOrdersId(id) != null) {
            List<InboundOrder> inboundOrders = inboundOrderService.selectByOrderId(id);
            List<OutboundOrder> outboundOrders = outboundOrderService.selectByOrderId(id);
            //mapList
            for (int i = 0; i < inboundOrders.size(); i++) {
                //入库
                Map<String, String> inDetailMap = new HashMap<String, String>();
                inDetailMap.put("title", inboundOrders.get(i).getCreateDate().toString().substring(0, 19));
                Integer inboundWarehouseId = inboundOrders.get(i).getWarehouseId();
                String area = locationsService.selectById(warehouseService.selectById(inboundWarehouseId).getArea()).getName();
                String inDetail = " [ " + area + " ] " + "  快件到达  " + " [ " + warehouseService.selectById(inboundWarehouseId).getName() + " ] ";
                inDetailMap.put("description", inDetail);
                mapList.add(inDetailMap);

                //出库
                Map<String, String> outDetailMap = new HashMap<String, String>();
                outDetailMap.put("title", outboundOrders.get(i).getCreateDate().toString().substring(0, 19));
                Integer outboundWarehouseId = outboundOrders.get(i).getWarehouseId();
                Integer nextWarehouseId = outboundOrders.get(i).getNextWarehouseId();
                String outArea = locationsService.selectById(warehouseService.selectById(outboundWarehouseId).getArea()).getName();
                String nextArea = locationsService.selectById(warehouseService.selectById(nextWarehouseId).getArea()).getName();
                String outDetail = "[ " + outArea + " ] " + " 快件离开 " + " [ " + warehouseService.selectById(outboundWarehouseId).getName() + " ] " + "  发往  " + " [ " + nextArea + " ] " + " [ " + warehouseService.selectById(inboundWarehouseId).getName() + " ] ";
                outDetailMap.put("description", outDetail);
                mapList.add(outDetailMap);
            }
            map.put("logisticsRecord", mapList);
            //status
            if (selectByOrdersId(id).getStatus().equals(2)) {
                map.put("status", "已签收");
            } else map.put("status", "配送中");
            //billId
            map.put("billId", id);
            resultMap.put("billData", map);
        } else resultMap.put("billData", false);
        return resultMap;
    }

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
    public List<Orders> selectByWarehouseId(Integer keyword, Integer id, Integer status, Integer offset) {
        String sql = "SELECT * FROM orders where warehouse_id = " + id;
        if (keyword != null) {
            sql += " AND order_id = " + keyword;
        }
        if (status != null) {
            sql += " AND status = " + status;
        }
        sql += " order by field(status,4,0,3,1,2),create_date DESC ";
        if (offset != null) {
            sql += " LIMIT " + offset + ",8";
        }
        return ordersMapper.selectByWarehouseId(sql);
    }

    @Override
    public Integer countByWarehouseId(Integer keyword, Integer id, Integer status) {
        String sql = "SELECT count(order_id) FROM orders where warehouse_id = " + id;
        if (keyword != null) {
            sql += " AND order_id = " + keyword;
        }
        if (status != null) {
            sql += " AND status = " + status;
        }
        return ordersMapper.countByWarehouseId(sql);
    }

    @Override
    public List<Orders> selectByCustomerId(Integer id, Integer status, Integer offset) {
        String sql = "SELECT * FROM orders where customer_id = " + id + " ";
        if (status != null) {
            if (status.equals(0)) sql += " status = 2";
            if (status.equals(1)) sql += "(status = 3 OR status = 1 OR status = 0 OR status = 4)";
        }
        sql += " ORDER BY create_date";
        if (offset != null) {
            sql += " LIMIT " + offset + ",8";
        }

        return ordersMapper.selectByCustomerId(sql);
    }

    @Override
    public Integer countByCustomerId(Integer id, Integer status) {
        String sql = "SELECT count(order_id) FROM orders where customer_id = " + id + " ";
        if (status != null) {
            sql += " status = " + status;
        }
        sql += " ORDER BY create_date";
        return ordersMapper.countByCustomerId(sql);
    }

    @Override
    public List<Orders> selectByReceiverPhone(String phone, Integer status, Integer offset) {
        String sql = "SELECT * FROM orders where receiver_phone = " + phone + " ";
        if (status != null) {
            if (status.equals(0)) sql += " status = 2";
            if (status.equals(1)) sql += "(status = 3 OR status = 1 OR status = 0 OR status = 4)";
        }
        sql += " ORDER BY create_date";
        if (offset != null) {
            sql += " LIMIT " + offset + ",8";
        }

        return ordersMapper.selectByReceiverPhone(sql);
    }

    @Override
    public Integer countByReceiverPhone(String phone, Integer status) {
        String sql = "SELECT count(order_id) FROM orders where receiver_phone = " + phone + " ";
        if (status != null) {
            if (status.equals(0)) sql += " status = 2";
            if (status.equals(1)) sql += "(status = 3 OR status = 1 OR status = 0 OR status = 4)";
        }

        sql += " ORDER BY create_date";
        return ordersMapper.countByReceiverPhone(sql);
    }

    @Override//终点区域仓库所有待配送的订单
    public List<Orders> getDelivery(Integer keyword,Integer warehouseId){
        String sql = "SELECT * FROM orders WHERE warehouse_id = "+ warehouseId +" AND end_warehouse_id = "+warehouseId +" AND status = 0 ";
        if (keyword != null){
            sql += " AND order_id = " + keyword + " ";
        }
        return ordersMapper.getDelivery(sql);
    }

    public Integer countByGetDelivery(Integer keyword,Integer warehouseId){
        String sql = "SELECT count(order_id) FROM orders WHERE warehouse_id = "+ warehouseId +" AND end_warehouse_id = "+warehouseId +" AND status = 0 ";
        if (keyword != null){
            sql += " AND order_id = " + keyword + " ";
        }
        return ordersMapper.countByGetDelivery(sql);
    }

    @Override//待送去其他中心仓库的订单
    public List<Orders> selectByWarehouseIdAndEnd(Integer warehouseId, Integer endWarehouseId) {
        String sql = "SELECT * FROM orders where status = 0" + " ";
        if (endWarehouseId != null) {
            sql += "ANd end_warehouse_id =" + endWarehouseId + " ";
        }
        if (warehouseId != null) {
            sql += "ANd warehouse_id =" + warehouseId + " ";
        }
        sql += "ORDER BY create_date ";
        List<Orders> orders = ordersMapper.selectByWarehouseIdAndEnd(sql);

        for (int i = 0; i < orders.size(); i++) {
            if (!warehouseService.selectById(orders.get(i).getWarehouseId()).getLevel().equals(2)) {
                orders.remove(i);
                i--;
            }
        }
        return orders;
    }

    @Override//这个中心仓库下属所有仓库的订单
    public List<Orders> selectByStatusAndWarehouseId(Integer warehouseId, Integer status) {
        Integer area = warehouseService.selectById(warehouseId).getArea();
        List<Locations> locations = locationsService.selectByParentId(area);
        List<Orders> orders = null;
        for (int i = 0; i < locations.size(); i++) {
            Integer countyWarehouseId = null;
            List<Orders> order = null;
            if (warehouseService.selectByAreaAndLevel(locations.get(i).getId(), 1) != null) {
                countyWarehouseId = warehouseService.selectByAreaAndLevel(locations.get(i).getId(), 1).getWarehouseId();
            }
            if (countyWarehouseId != null) {
                order = selectByWarehouseId(null, countyWarehouseId, status, null);
            }
            if (order != null) {
                if (orders != null) {
                    orders.addAll(order);
                } else orders = order;
            }
        }
        return orders;
    }

    @Override
    public List<Orders> selectByUserIdAndOrderStatus(Integer keyword, Integer id, Integer status, Integer offset) {
        String sql = "SELECT * FROM orders WHERE 1=1 ";
        if (keyword != null) {
            sql += "AND order_id = " + keyword + " ";
        }
        sql += "AND user_id = " + id + " AND status = " + status + " ";
        if (offset != null) {
            sql += "LIMIT " + offset + ",8 ";
        }
        if (userService.selectById(id).getPriority().equals(DELIVER.getCode())) {
            return ordersMapper.selectByUserIdAndOrderStatus(sql);
        } else return new ArrayList<Orders>();
    }

    @Override
    public Integer countByUserIdAndOrderStatus(Integer keyword, Integer id, Integer status) {
        String sql = "SELECT count(order_id) FROM orders WHERE 1=1 ";
        if (keyword != null) {
            sql += "AND order_id = " + keyword + " ";
        }
        sql += "AND user_id = " + id + " AND status = " + status + " ";
        if (userService.selectById(id).getPriority().equals(DELIVER.getCode())) {
            return ordersMapper.countByUserIdAndOrderStatus(sql);
        } else return null;
    }

    @Override
    public Map<String, Object> finish(Integer orderId) {
        Map<String, Object> map = new HashMap<String, Object>();
        OutboundOrder outboundOrder = outboundOrderService.selectByOrderIdAnd(orderId, 0);
        outboundOrder.setDelMark(1);
        if (!outboundOrderService.update(outboundOrder)) {
            map.put("result", "出库表更新失败！");
        } else {
            Orders orders = selectByOrdersId(orderId);
            orders.setStatus(WAS_FINISHED.getCode());
            if (ordersMapper.update(orders)) {
                map.put("result", true);
            } else map.put("result", "订单状态更新失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> driverFinish(Integer handoverOrderId) throws ParseException {
        Map<String, Object> map = new HashMap<String, Object>();
        int flag = 0;
        //交接单的所有出库单记录
        List<HandoverOrder> handoverOrders = handoverOrderService.selectByHandoverOrderId(handoverOrderId);
        List<Long> orders = new ArrayList<Long>();
        for (int i = 0; i < handoverOrders.size(); i++) {
            HandoverOrder handoverOrder = handoverOrders.get(i);
            handoverOrder.setDelMark(1);
            handoverOrderService.update(handoverOrder);
            //出库单的所有订单记录
            List<OutboundOrder> outboundOrders = outboundOrderService.selectById(handoverOrders.get(i).getOutboundId());
            for (int j = 0; j < outboundOrders.size(); j++) {
                //出库单更新状态
//                OutboundOrder outboundOrder = outboundOrders.get(j);
//                outboundOrder.setDelMark(1);
//                outboundOrderService.update(outboundOrder);
                //入库单
                Orders order = ordersMapper.selectByOrdersId(outboundOrders.get(j).getOrderId());
                order.setStatus(WAIT_FOR.getCode());
                ordersMapper.update(order);
                flag++;
                orders.add(Long.valueOf(order.getOrderId()));
            }
        }
        Integer warehouseId = handoverOrderService.selectByHandoverOrderId(handoverOrderId).get(0).getWarehouseId();
        inboundOrderService.insert(orders, warehouseId);
        if (flag == orders.size()) {
            map.put("result", true);
        } else map.put("result", false);
        return map;
    }

    @Override
    public Map<String, Object> outboundConfirm(Integer outboundOrderId) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<OutboundOrder> outboundOrders = outboundOrderService.selectById(outboundOrderId);
        for (int i = 0; i < outboundOrders.size(); i++) {
            //出库单更新状态
            OutboundOrder outboundOrder = outboundOrders.get(i);
            outboundOrder.setDelMark(1);
            if (!outboundOrderService.update(outboundOrder)) {
                map.put("result", "出库单更新状态失败");
            } else {
                //订单更新状态
                Orders order = ordersMapper.selectByOrdersId(outboundOrder.getOrderId());
                order.setStatus(IS_DELIVERING.getCode());
                if (ordersMapper.update(order)) {
                    map.put("result", true);
                } else map.put("result", "订单状态更新失败");
            }
        }
        return map;
    }

    @Override
    public Orders selectByOrdersId(Integer id) {
        return ordersMapper.selectByOrdersId(id);
    }

    @Override
    public Boolean checkArea(String area) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> areaMap = locationsService.selectLocationsByAddress(area);
        Integer areaId = 0;
        if (areaMap.containsKey("city")) {
            if (areaMap.get("city") != null) {
                Locations location = (Locations) areaMap.get("city");
                areaId = location.getId();
            }
        } else if (areaMap.containsKey("county")) {
            if (areaMap.get("county") != null) {
                Locations location = (Locations) areaMap.get("county");
                areaId = location.getId();
            }
        } else return false;
        if (warehouseService.selectByArea(areaId).size() > 0) {
            return true;
        } else return false;
    }

    @Override
    public Boolean setEndWarehouse(Integer id) {
        Orders orders = selectByOrdersId(id);
        //收件人的地址判断
        Map<String, Object> map = locationsService.selectLocationsByAddress(orders.getReceiverAddress());
        Locations receiverLocation = (Locations) map.get("city");
        Integer area = receiverLocation.getId();
        //寄件人的得知判断
        Map<String, Object> cusMap = locationsService.selectLocationsByAddress(orders.getCustomerAddress());
        Locations customerLocation = (Locations) cusMap.get("county");
        Integer customerArea = customerLocation.getId();
        //这里为开始的区域仓库到中心仓库
        if (warehouseService.selectByAreaAndLevel(customerArea, 1).getWarehouseId().equals(orders.getWarehouseId())) {
            Integer centerAre = customerLocation.getParentId();
            Integer centerWarehouseId = warehouseService.selectByAreaAndLevel(centerAre, 2).getWarehouseId();
            orders.setEndWarehouseId(centerWarehouseId);
        } else if (warehouseService.selectByAreaAndLevel(area, 2) != null) {//到终点的中心仓库的判断
            //终点中心仓库到区域仓库
            if (!warehouseService.selectByAreaAndLevel(area, 2).getWarehouseId().equals(orders.getWarehouseId())) {
                if (!orders.getWarehouseId().equals(orders.getEndWarehouseId())) {
                    orders.setEndWarehouseId(warehouseService.selectByAreaAndLevel(area, 2).getWarehouseId());//中心仓库之间
                }
            } else {
                Locations locations = (Locations) map.get("county");
                area = locations.getId();
                Warehouse warehouse = warehouseService.selectByAreaAndLevel(area, 1);
                if (warehouse != null) {
                    orders.setEndWarehouseId(warehouse.getWarehouseId());
                    orders.setStatus(WAIT_FRO_TO_THEEND.getCode());
                }
            }
        } else return false;
        if (ordersMapper.update(orders)) {
            return true;
        } else return false;
    }

    @Override
    public String insert(Orders orders) {
        Map<String, Object> customerAddMap = locationsService.selectLocationsByAddress(orders.getCustomerAddress());
        Locations customerProvince = (Locations) customerAddMap.get("province");
        orders.setCustomerProvince(customerProvince.getName());
        Locations customerCity = (Locations) customerAddMap.get("city");
        orders.setCustomerCity(customerCity.getName());

        Map<String, Object> receiverAddMap = locationsService.selectLocationsByAddress(orders.getReceiverAddress());
        Locations receiverProvince = (Locations) receiverAddMap.get("province");
        orders.setReceiverProvince(receiverProvince.getName());
        Locations receiverCity = (Locations) receiverAddMap.get("city");
        orders.setReceiverCity(receiverCity.getName());

        if (customerService.selectById(orders.getCustomerId()) == null) {
            return "无此用户";
        } else if (ordersMapper.insert(orders)) {
            return "插入成功";
        } else return "插入失败";


    }

    @Override
    public String update(Orders orders) {
         if (ordersMapper.update(orders)) {
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
