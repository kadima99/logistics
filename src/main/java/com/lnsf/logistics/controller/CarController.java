package com.lnsf.logistics.controller;

import com.lnsf.logistics.entity.*;
import com.lnsf.logistics.service.*;
import com.lnsf.logistics.service.impl.CarServiceImpl;
import com.lnsf.logistics.service.impl.UserServiceImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lnsf.logistics.Enum.CarLevel.LEVEL_1;
import static com.lnsf.logistics.Enum.CarLevel.LEVEL_2;
import static com.lnsf.logistics.Enum.CarStatus.*;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;
    @Autowired
    private LineService lineService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private OutboundOrderService outboundOrderService;
    @Autowired
    private HandoverOrderService handoverOrderService;

    @RequestMapping("/getHistoryLocal")
    public Map<String, Object> getHistoryLocal(Integer keyword, Integer page, Integer level, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer offset = (page - 1) * 8;
        if (user != null) {
            List<Car> cars = carService.selectByWarehouseId(keyword, user.getWarehouseId(), level, GIFT.getCode(), offset);
            map.put("carData", cars);
            map.put("totalPage", Math.ceil(carService.countByWarehouseId(keyword, user.getWarehouseId(), level, GIFT.getCode()).doubleValue() / 8.0));
        }
        return map;
    }

    @RequestMapping("/getLocal")
    public Map<String, Object> getLocal(Integer keyword, Integer page, Integer level, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer offset = (page - 1) * 8;
        if (user != null) {
            List<Car> cars = carService.selectByWarehouseId(keyword, user.getWarehouseId(), level, null, offset);
            map.put("carData", cars);
            map.put("totalPage", Math.ceil(carService.countByWarehouseId(keyword, user.getWarehouseId(), level, null).doubleValue() / 8.0));
        }
        return map;
    }

    @RequestMapping("/delete")
    public Map<String, Object> delete(Integer carId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Car car = carService.selectById(carId);
            car.setStatus(GIFT.getCode());
            if (carService.update(car).equals("更新成功")) {
                map.put("result", true);
            } else map.put("result", carService.update(car));
        }
        return map;
    }

    @RequestMapping("/add")
    public Map<String, Object> add(Integer level, Float maxWeight, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Car car = new Car(maxWeight, maxWeight, NO_BUSY.getCode(), level, user.getWarehouseId(), user.getWarehouseId());
            if (carService.insert(car).equals("插入成功")) {
                map.put("result", true);
            } else map.put("result", carService.insert(car));
        }
        return map;
    }

    @RequestMapping("/getLine")
    public List<Map<String, Object>> getLine(HttpServletRequest request) {
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Car car = carService.selectByUserId(user.getUserId());
            if (car != null) {
                if (!car.getLineId().equals(0)) {
                    Line line = lineService.selectById(car.getLineId());
                    String[] lineSummary = line.getLineSummary().split("-");
                    for (int i = 0; i < lineSummary.length; i++) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("name", warehouseService.selectById(Integer.valueOf(lineSummary[i])).getName());
                        map.put("address", warehouseService.selectById(Integer.valueOf(lineSummary[i])).getAddress());
                        mapList.add(map);
                    }
                }
            }
        }
        return mapList;
    }

    @RequestMapping("/adjust")
    public Map<String, Object> adjust(Integer page, Integer level, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        Integer offset = (page - 1) * 8;
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Car> cars = carService.selectByWarehouseId(null, user.getWarehouseId(), level, NO_BUSY.getCode(), offset);
            map.put("carData", cars);
            map.put("totalPage", Math.ceil(carService.countByWarehouseId(null, user.getWarehouseId(), level, NO_BUSY.getCode()).doubleValue() / 8.0));
        }
        return map;
    }

    @RequestMapping("/packageShipment")//送件装车
    public Map<String, Object> packageShipment(Integer carId, Integer userId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Car car = carService.selectById(carId);
            car.setUserId(userId);
            car.setStatus(IS_BUSY.getCode());
            Integer level = car.getLevel();
            if (level.equals(LEVEL_1.getCode())) {
                Map<String, Object> inMap = lineService.getRouteByCenterWarehouse(user.getWarehouseId(), 3);
                String lineSummary = (String) inMap.get("route");
                String[] routes = lineSummary.split("-");
                Line line = new Line(lineSummary, Integer.parseInt(routes[0]), Integer.parseInt(routes[0]), 0);
                if (lineService.insert(line)) {
                    Line line1 = lineService.selectByAll(lineSummary, Integer.parseInt(routes[0]), Integer.parseInt(routes[0]));
                    car.setLineId(line1.getLineId());
                }

                //一个仓库对应一个出库单
                Integer[] outboundOrderIds = new Integer[routes.length-1];
                for (int  i = 1; i < routes.length; i++) {//多个仓库
                    Integer warehouse_id = Integer.parseInt(routes[i]);
                    if (!warehouse_id.equals(Integer.parseInt(routes[0]))) {
                        List<Orders> orders = ordersService.selectByStatusAndEndWarehouseId(warehouse_id, 3);
                        //生成这个仓库的出库单
                        outboundOrderService.insert(orders, Integer.parseInt(routes[0]), warehouse_id);
                        //所有出库单号
                        Integer[] add = outboundOrderService.selectByNextWarehouseIdAndDelMark(warehouse_id, 0);
                        //拼接,因为一个仓库对应一个出库单，所以despos为i-1位
                        System.arraycopy(add, 0, outboundOrderIds, i-1, add.length);
                    }
                }
                for (int j =0;j<outboundOrderIds.length;j++){
                    System.out.println("长度"+outboundOrderIds.length);
                    System.out.println("仓库"+outboundOrderIds[j]);
                }
                handoverOrderService.inboundInsert(userId, outboundOrderIds, car.getLineId());
                //car.setLineId(lineService.select);
            }
            if (level.equals(LEVEL_2.getCode())) {
                Map<String, Object> endMap = lineService.getEndWarehouseId(user.getWarehouseId());
                Integer endWarehouseId = Integer.parseInt(endMap.get("endWarehouseId").toString());
                Map<String, Object> outMap = lineService.getRouteByCenterWarehouseToCenterWarehouse(user.getWarehouseId(), endWarehouseId);
                String lineSummary = (String) outMap.get("primRoute");
                String[] routes = lineSummary.split("-");
                Line line = new Line(lineSummary, Integer.parseInt(routes[0]), Integer.parseInt(routes[routes.length - 1]), 0);
                if (lineService.insert(line)) {
                    Line line1 = lineService.selectByAll(lineSummary, Integer.parseInt(routes[0]), Integer.parseInt(routes[routes.length - 1]));
                    car.setLineId(line1.getLineId());
                }
                for (String route : routes) {//多个仓库
                    Integer warehouse_id = Integer.parseInt(route);
                    List<Orders> orders = ordersService.selectByWarehouseId(null, warehouse_id, 0, null);
                    //生成这个仓库的出库单
                    outboundOrderService.insert(orders, warehouse_id, Integer.parseInt(routes[routes.length - 1]));
                }
                Integer[] outboundOrderIds = outboundOrderService.selectByNextWarehouseIdAndDelMark(Integer.parseInt(routes[routes.length - 1]), 0);
                handoverOrderService.centerInboundInsert(userId, outboundOrderIds, car.getLineId());
            }
            if (carService.update(car).equals("更新成功")) {
                map.put("result", true);
            } else map.put("result", carService.update(car));
        }
        return map;
    }

    @RequestMapping("/deliverShipment")//揽件装车
    public Map<String, Object> deliverShipment(Integer carId, Integer userId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Car car = carService.selectById(carId);
            car.setUserId(userId);
            car.setStatus(IS_BUSY.getCode());
            Map<String, Object> outMap = lineService.getRouteByCenterWarehouse(user.getWarehouseId(), 0);
            String lineSummary = (String) outMap.get("route");
            String[] routes = lineSummary.split("-");
            Line line = new Line(lineSummary, Integer.parseInt(routes[0]), Integer.parseInt(routes[0]), 0);
            if (lineService.insert(line)) {
                Line line1 = lineService.selectByAll(lineSummary, Integer.parseInt(routes[0]), Integer.parseInt(routes[0]));
                car.setLineId(line1.getLineId());
            }
            for (int i = 1; i < routes.length; i++) {//多个仓库
                Integer warehouse_id = Integer.parseInt(routes[i]);
                List<Orders> orders = ordersService.selectByWarehouseId(null, warehouse_id, 0, null);
                //生成这个仓库的出库单
                outboundOrderService.insert(orders, warehouse_id, Integer.parseInt(routes[0]));
            }
            Integer[] outboundOrderIds = outboundOrderService.selectByNextWarehouseIdAndDelMark(Integer.parseInt(routes[0]), 0);
            handoverOrderService.outboundInsert(userId, outboundOrderIds, car.getLineId());
            if (carService.update(car).equals("更新成功")) {
                map.put("result", true);
            } else map.put("result", carService.update(car));
        }
        return map;
    }


}
