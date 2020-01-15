package com.lnsf.logistics.controller;


import com.lnsf.logistics.entity.*;
import com.lnsf.logistics.service.*;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lnsf.logistics.Enum.CarStatus.IS_BUSY;
import static com.lnsf.logistics.Enum.CarStatus.NO_BUSY;
import static com.lnsf.logistics.Enum.OrdersStatus.*;

@RestController
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private UserService userService;
    @Autowired
    private LocationsService locationsService;
    @Autowired
    private HandoverOrderService handoverOrderService;
    @Autowired
    private OutboundOrderService outboundOrderService;
    @Autowired
    private InboundOrderService inboundOrderService;
    @Autowired
    private CarService carService;
    @Autowired
    private  LineService lineService;


    @RequestMapping("/inbound")
    public Map<String,Object> inbound(@RequestParam("orderId") List<Long> orderId,HttpServletRequest request) throws ParseException {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if ( user != null) {
            map = inboundOrderService.insert(orderId,user.getWarehouseId());
        }
        return map;
    }

    @RequestMapping("/checkArea")
    public Boolean checkArea(String area, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        if (session.getAttribute("customer") != null) {
            return ordersService.checkArea(area);
        } else return false;
    }

    @RequestMapping("/create")
    public Map<String, Object> create(String customerName, String customerPhone, String customerAddress, String receiverName, String receiverPhone, String receiverAddress, Integer warehouseId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            Orders orders = new Orders(customer.getCustomerId(), customerName, customerPhone, customerAddress, receiverName, receiverPhone, receiverAddress, 4, warehouseId);
            if (ordersService.insert(orders).equals("插入成功")) {
                System.out.println("wo jin");
                map.put("result", true);
            } else map.put("result", ordersService.insert(orders));
        }
        return map;
    }

    @RequestMapping("/getDetails")
    public Map<String, Object> getDetails(Integer billId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        if (session.getAttribute("customer") != null) {
            map = ordersService.getDetails(billId);
        }
        return map;
    }

    @RequestMapping("/getHistoryDeliver")
    public Map<String, Object> getHistoryDeliver(Integer keyword, Integer page, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Integer offset = (page - 1) * 8;
            map.put("orderData", ordersService.selectByUserIdAndOrderStatus(keyword, user.getUserId(), WAS_FINISHED.getCode(), offset));
            map.put("totalPage", Math.ceil(ordersService.countByUserIdAndOrderStatus(keyword, user.getUserId(), WAS_FINISHED.getCode()).doubleValue() / 8.0));
        }
        return map;
    }

    @RequestMapping("/finish")//配送员订单送达
    public Map<String, Object> finish(Integer orderId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            map = ordersService.finish(orderId);
        }
        return map;
    }

    @RequestMapping("/getDelivering")
    public Map<String, Object> getDelivering(Integer keyword, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            map.put("orderData", ordersService.selectByUserIdAndOrderStatus(keyword, user.getUserId(), IS_DELIVERING.getCode(), null));
            map.put("totalPage", Math.ceil(ordersService.countByUserIdAndOrderStatus(keyword, user.getUserId(), IS_DELIVERING.getCode()).doubleValue() / 8.0));
        }
        return map;
    }

    @RequestMapping("/allOutboundConfirm1")
    public Map<String, Object> allOutboundConfirm1(Integer handoverOrderId, HttpServletRequest request) throws ParseException {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Car car = carService.selectByUserId(user.getUserId());
            Line line = lineService.selectById(car.getLineId());
            System.out.println(car.getCarId());
            car.setUserId(0);
            car.setLineId(0);
            car.setStatus(NO_BUSY.getCode());
            carService.update(car);
            line.setDelMark(1);
            lineService.update(line);
            map = ordersService.driverFinish(handoverOrderId);
        }
        return map;
    }

    @RequestMapping("/allOutboundConfirm")
    public Map<String, Object> driverFinish(Integer handoverOrderId, HttpServletRequest request) throws ParseException {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Car car = carService.selectByUserId(user.getUserId());
            Line line = lineService.selectById(car.getLineId());
            car.setUserId(0);
            car.setLineId(0);
            car.setStatus(NO_BUSY.getCode());
            carService.update(car);
            line.setDelMark(1);
            lineService.update(line);
            map = ordersService.driverFinish(handoverOrderId);
        }
        return map;
    }

    @RequestMapping("/outboundConfirm")
    public Map<String, Object> outboundConfirm(Integer outboundOrderId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            map = ordersService.outboundConfirm(outboundOrderId);
        }
        return map;
    }

    @RequestMapping("/getHandover")
    public Map<String, Object> getHandover(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        if (user != null) {
            Integer warehouseId = 0;
            List<HandoverOrder> handoverOrders = handoverOrderService.selectByUserIdAndStatus(null, user.getUserId(), 0);
            for (int i = 0; i < handoverOrders.size(); i++) {
                Map<String, Object> handoverOrderMap = new HashMap<String, Object>();
                handoverOrderMap.put("outboundOrderId", handoverOrders.get(i).getOutboundId());
                handoverOrderMap.put("warehouseId", handoverOrders.get(i).getWarehouseId());
                handoverOrderMap.put("createDate", handoverOrders.get(i).getCreateDate());
                handoverOrderMap.put("status", outboundOrderService.selectById( handoverOrders.get(i).getOutboundId()).get(0).getDelMark());
                List<OutboundOrder> outboundOrders = outboundOrderService.selectById(handoverOrders.get(i).getOutboundId());
                Integer[] orderDetail = new Integer[outboundOrders.size()];
                for (int j = 0; j < outboundOrders.size(); j++) {
                    Integer orderId = outboundOrders.get(j).getOrderId();
                    orderDetail[j] = orderId;
                }
                handoverOrderMap.put("orderDetial", orderDetail);
                mapList.add(handoverOrderMap);
            }

            map.put("handoverOrderData", mapList);
            if (handoverOrders.size() > 0) {
                map.put("handoverOrderStatus",handoverOrders.get(0).getFlag());
                map.put("handoverOrderId", handoverOrders.get(0).getHandoverOrderId());
            }

        }
        return map;
    }

    @RequestMapping("/getHistoryHandover")
    public Map<String, Object> getHistoryHandover(Integer page, Integer keyword, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer offset = (page - 1) * 8;
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        if (user != null) {
            List<Integer> handoverOrderIds = handoverOrderService.getHandoverOrderIdByUserId(user.getUserId(), offset);
            //得到这个司机所有完成的交接单
            for (int i = 0; i < handoverOrderIds.size(); i++) {
                List<HandoverOrder> handoverOrders = handoverOrderService.selectByHandoverOrderId(handoverOrderIds.get(i));
                Map<String, Object> handoverOrderMap = new HashMap<String, Object>();
                handoverOrderMap.put("handoverOrderId", handoverOrderIds.get(i));
                handoverOrderMap.put("createDate", handoverOrders.get(0).getCreateDate());
                Integer[] outboundOrderDetail = new Integer[handoverOrders.size()];
                for (int j = 0; j < handoverOrders.size(); j++) {
                    Integer outboundOrder = handoverOrders.get(j).getOutboundId();
                    outboundOrderDetail[j] = outboundOrder;
                }
                handoverOrderMap.put("orderDetial", outboundOrderDetail);
                mapList.add(handoverOrderMap);
            }
            map.put("handoverOrderData", mapList);
            if (handoverOrderIds.size() > 0){
                map.put("totalPage", Math.ceil(handoverOrderService.countHandoverOrderIdByUserId(user.getUserId()).doubleValue() / 8.0));
            }
        }
        return map;
    }

    @RequestMapping("/getAll")
    public Map<String, Object> getAll(Integer keyword, Integer status, Integer page, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer offset = (page - 1) * 8;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Orders> orders = ordersService.selectByWarehouseId(keyword, null, status, offset);
            map.put("orderData", orders);
            map.put("totalPage", Math.ceil(ordersService.countByWarehouseId(keyword, null, status).doubleValue() / 8.0));
        }
        return map;
    }

    @RequestMapping("/getLocal")
    public Map<String, Object> getLocal(Integer keyword, Integer status, Integer page, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer offset = (page - 1) * 8;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Orders> orders = ordersService.selectByWarehouseId(keyword, user.getWarehouseId(), status, offset);
            map.put("orderData", orders);
            map.put("totalPage", Math.ceil(ordersService.countByWarehouseId(keyword, user.getWarehouseId(), status).doubleValue() / 8.0));
        }
        return map;
    }

    @RequestMapping("/getLocalNew")
    public Map<String, Object> getLocalNew(Integer keyword, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Orders> orders = ordersService.selectByWarehouseId(keyword, user.getWarehouseId(), 4, null);
            map.put("orderData", orders);
            map.put("totalPage", Math.ceil(ordersService.countByWarehouseId(keyword, user.getWarehouseId(), 4).doubleValue() / 8.0));
        }
        return map;
    }

    @RequestMapping("/confirm")
    public Map<String, Object> confirm(Integer orderId, Float wareWeight, Float freight, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Orders order = ordersService.selectByOrdersId(orderId);
            order.setWareWeight(wareWeight);
            order.setFreight(freight);
            if (ordersService.update(order).equals("更新成功")) {
                map.put("result", true);
            } else map.put("result", ordersService.update(order));
        }
        return map;
    }

    @RequestMapping("/getInbound")
    public Map<String, Object> getInbound(Integer page, Integer status, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer offset = (page - 1) * 8;
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        if (user != null) {
            List<Integer> inboundOrderIds = inboundOrderService.getInboundOrderIdByWarehouseId(user.getWarehouseId(), offset);
            //得到这个司机所有完成的交接单
            for (int i = 0; i < inboundOrderIds.size(); i++) {
                List<InboundOrder> inboundOrders = inboundOrderService.selectById(inboundOrderIds.get(i));
                Map<String, Object> inboundOrderMap = new HashMap<String, Object>();
                inboundOrderMap.put("inboundOrderId", inboundOrderIds.get(i));
                inboundOrderMap.put("createDate", inboundOrders.get(0).getCreateDate());
                inboundOrderMap.put("status", inboundOrders.get(0).getDelMark());
                Integer[] orderDetail = new Integer[inboundOrders.size()];
                for (int j = 0; j < inboundOrders.size(); j++) {
                    Integer orderId = inboundOrders.get(j).getOrderId();
                    orderDetail[j] = orderId;
                }
                inboundOrderMap.put("orderDetial", orderDetail);
                mapList.add(inboundOrderMap);
            }
            map.put("inboundOrderData", mapList);
            if (inboundOrderIds.size() > 0){
                map.put("totalPage", Math.ceil(inboundOrderService.countInboundOrderIdByWarehouseId(user.getWarehouseId()).doubleValue() / 8.0));

            }
        }
        return map;
    }

    @RequestMapping("/getOutbound")
    public Map<String, Object> getOutbound(Integer page, Integer status, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer offset = (page - 1) * 8;
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        if (user != null) {
            List<Integer> outboundOrderIds = outboundOrderService.getOutboundOrderIdByWarehouseId(user.getWarehouseId(), offset);
            //得到这个司机所有完成的交接单
            for (int i = 0; i < outboundOrderIds.size(); i++) {
                List<OutboundOrder> outboundOrders = outboundOrderService.selectById(outboundOrderIds.get(i));
                Map<String, Object> outboundOrderMap = new HashMap<String, Object>();
                outboundOrderMap.put("outboundOrderId", outboundOrderIds.get(i));
                outboundOrderMap.put("createDate", outboundOrders.get(0).getCreateDate());
                outboundOrderMap.put("status", outboundOrders.get(0).getDelMark());
                Integer[] orderDetail = new Integer[outboundOrders.size()];
                for (int j = 0; j < outboundOrders.size(); j++) {
                    Integer orderId = outboundOrders.get(j).getOrderId();
                    orderDetail[j] = orderId;
                }
                outboundOrderMap.put("orderDetial", orderDetail);
                mapList.add(outboundOrderMap);
            }
            map.put("outboundOrderData", mapList);
            map.put("totalPage", Math.ceil(outboundOrderService.countOutboundOrderIdByWarehouseId(user.getWarehouseId()).doubleValue() / 8.0));
        }
        return map;
    }

    @RequestMapping("/getDelivery")
    public Map<String, Object> getDelivery(Integer keyword, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Orders> orders = ordersService.getDelivery(keyword, user.getWarehouseId());
            map.put("orderData", orders);
            map.put("totalPage", Math.ceil(ordersService.countByGetDelivery(keyword, user.getWarehouseId()).doubleValue() / 8.0));
        }
        return map;
    }

    @RequestMapping("/deliverAdjust")
    public Map<String, Object> deliverAdjust( @RequestParam("orderId") List<Long> orderId,Integer userId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Orders> orders = new ArrayList<Orders>();

            int flag = 0;
            for (int i = 0; i < orderId.size(); i++) {
                Orders order = ordersService.selectByOrdersId(orderId.get(i).intValue());
                order.setUserId(userId);
                User orderUser = userService.selectById(userId);
                orderUser.setStatus(IS_BUSY.getCode());
                order.setStatus(IS_DELIVERING.getCode());
                if (ordersService.update(order).equals("更新成功")) {
                    flag++;
                }
                orders.add(order);
            }
            outboundOrderService.insert(orders,user.getWarehouseId(),user.getWarehouseId());
            if (flag == orderId.size()){
                map.put("result",true);
            }else map.put("result","分配失败");
        }
        return map;
    }

}
