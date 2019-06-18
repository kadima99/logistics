package com.lnsf.logistics.service.impl;

import com.lnsf.logistics.entity.*;
import com.lnsf.logistics.mapper.LineMapper;
import com.lnsf.logistics.route.ListUDG;
import com.lnsf.logistics.route.SnCal;
import com.lnsf.logistics.service.LineService;
import com.lnsf.logistics.service.NearbyWarehouseService;
import com.lnsf.logistics.service.OrdersService;
import com.lnsf.logistics.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class LineServiceImpl implements LineService {

    @Autowired
    private LineMapper lineMapper;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private NearbyWarehouseService nearbyWarehouseService;

    SnCal snCal = new SnCal();

    @Override
    public List<Line> selectAll() {
        return lineMapper.selectAll();
    }

    @Override
    public List<Line> selectByBeginId(Integer id) {
        return lineMapper.selectByBeginId(id);
    }

    @Override
    public List<Line> selectByBeginIdEndId(Integer beginId, Integer endId) {
        return lineMapper.selectByBeginIdEndId(beginId, endId);
    }

    @Override
    public Line selectById(Integer id) {
        return lineMapper.selectById(id);
    }

    @Override
    public Boolean insert(List<Integer> allWarehouseId, Integer beginId, Integer endId) {
        String lineSummary = allWarehouseId.toString();
        return lineMapper.insert(new Line(lineSummary, beginId, endId, 0));
    }

    @Override
    public Boolean update(Line line) {
        return lineMapper.update(line);
    }

    @Override
    public Boolean delete(Integer id) {
        return lineMapper.delete(id);
    }

    @Override
    public Map<String, Object> getRouteByCenterWarehouse(Integer centerWarehouseId) {
        Map<String, Object> map = new HashMap<>();
        map.put("centerWareHouseId", centerWarehouseId);
        List<Warehouse> warehouseList = warehouseService.selectByArea(warehouseService.selectById(centerWarehouseId).getArea());
        List<Orders> ordersList = ordersService.selectByStatusAndWarehouseId(centerWarehouseId, 0);
        Map<Integer, Integer> orderMap = new HashMap<>();
        for (int i = 0; i < ordersList.size(); i++) {

            if (!orderMap.containsKey(ordersList.get(i).getWarehouseId())) {
                orderMap.put(ordersList.get(i).getWarehouseId(), 1);
            } else {
                Integer num = orderMap.get(ordersList.get(i).getWarehouseId());
                orderMap.put(ordersList.get(i).getWarehouseId(), ++num);
            }
        }
        map.put("orderList", ordersList);
        map.put("warehouseList", warehouseList);
        map.put("orderMap", orderMap);

        Map<Integer, Character> t1 = new HashMap<>();
        Map<Integer, Character> t2 = new HashMap<>();

        //赋值给顶点数组
        Integer orderSize = orderMap.size() + 1;
        System.out.println(orderSize);
        char[] vemx = new char[orderSize];
        Integer i = 1;

        t1.put(centerWarehouseId, 'A');
        t2.put(centerWarehouseId, 'A');
        vemx[0] = 'A';
        for (Integer in : orderMap.keySet()) {
            t1.put(in, (char) ('A' + i));
            t2.put(in, (char) ('A' + i));
            vemx[i] = t1.get(in);
            i++;
        }
        map.put("t1", t1);
        map.put("t2", t2);
        Integer sum = 0;
        for (int flag = 1; flag < orderSize; flag++) {
            sum += flag;
            System.out.println(flag);
        }

        ListUDG.EData[] eData = new ListUDG.EData[sum];

        Iterator<Map.Entry<Integer, Character>> one = t1.entrySet().iterator();
        Integer j = 0;
        while (one.hasNext()) {
            Map.Entry<Integer, Character> entryOne = one.next();
            Iterator<Map.Entry<Integer, Character>> two = t2.entrySet().iterator();
            while (two.hasNext()) {
                Map.Entry<Integer, Character> entryTwo = two.next();
                if (entryOne.getValue() != entryTwo.getValue() && entryOne.getValue() < entryTwo.getValue()) {
                    eData[j] = new ListUDG.EData(entryOne.getValue(), entryTwo.getValue(), getDistanceByWareHouseId(entryOne.getKey(), entryTwo.getKey()));
                    System.out.println(eData[j].getWeight());
                    j++;
                } else
                    continue;
            }
        }
        //System.out.println(eData[0].getStart()+eData[0].getEnd());
        ListUDG PG = new ListUDG(vemx, eData);
        PG.print();
        PG.DFS();
        PG.BFS();
        PG.kruskal();
        char[] prim = PG.prim(0);
        String route = "";
        //route += t1.get(prim[0])+"-";
        Integer primNum = 0;

        for (int m = 0; m < prim.length; m++) {
            Iterator<Map.Entry<Integer, Character>> three = t1.entrySet().iterator();
            while (three.hasNext()) {
                Map.Entry<Integer, Character> entryThree = three.next();
                if (prim[m] == entryThree.getValue())
                    if (m == prim.length - 1) {
                        route += entryThree.getKey();
                    } else route += entryThree.getKey() + "-";
                else
                    continue;
            }

        }
        map.put("route", route);
        //System.out.println(route);

//        int[][] path = new int[vemx.length][vemx.length];
//        int[][] floy = new int[vemx.length][vemx.length];
//        // floyd算法获取各个顶点之间的最短距离
//        PG.floyd(path, floy);
        return map;
    }


    @Override
    public Map<String, Object> getRouteByCenterWarehouseToCenterWarehouse(Integer startCenterWarehouseId, Integer endCenterWarehouseId) {

        Map<String, Object> map = new HashMap<>();


        List<NearbyWarehouse> nearbyWarehouseList = nearbyWarehouseService.selectAll();

        Map<Integer, Integer> nearbyWarehouseMap = new HashMap<>();

        for (int i = 0; i < nearbyWarehouseList.size(); i++) {
            nearbyWarehouseMap.put(nearbyWarehouseList.get(i).getWarehouseId(), 1);
            nearbyWarehouseMap.put(nearbyWarehouseList.get(i).getNearbyWarehouseId(), 1);
        }
        nearbyWarehouseMap.put(startCenterWarehouseId, 0);//起点仓库
        nearbyWarehouseMap.put(endCenterWarehouseId, -1);//终点仓库

        Map<Integer, Character> t1 = new HashMap<>();
        Map<Integer, Character> t2 = new HashMap<>();

        Integer WarehouseSize = nearbyWarehouseMap.size();
        char[] vemx = new char[WarehouseSize];
        Integer flag = 1;

        vemx[0] = 'A';
        t1.put(startCenterWarehouseId, 'A');
        t2.put(startCenterWarehouseId, 'A');
        for (Integer in : nearbyWarehouseMap.keySet()) {
            if (!in.equals(startCenterWarehouseId) && !in.equals(endCenterWarehouseId)) {
                t1.put(in, (char) ('A' + flag));
                t2.put(in, (char) ('A' + flag));
                vemx[flag] = t1.get(in);
                flag++;
            }

        }
        t1.put(endCenterWarehouseId, (char) ('A' + flag));
        t2.put(endCenterWarehouseId, (char) ('A' + flag));
        vemx[flag] = t1.get(endCenterWarehouseId);
        System.out.println(vemx);


        Iterator<Map.Entry<Integer, Character>> one = t1.entrySet().iterator();
        Set<NearbyWarehouse> nearbyWarehouseSet = new HashSet<>();
        Integer j = 0;
        while (one.hasNext()) {
            Map.Entry<Integer, Character> entryOne = one.next();
            Iterator<Map.Entry<Integer, Character>> two = t2.entrySet().iterator();
            while (two.hasNext()) {
                Map.Entry<Integer, Character> entryTwo = two.next();

                if (nearbyWarehouseService.selectByWarehouseIdAndOthers(entryOne.getKey(), entryTwo.getKey()) != null) {

                    nearbyWarehouseSet.add(nearbyWarehouseService.selectByWarehouseIdAndOthers(entryOne.getKey(), entryTwo.getKey()));
                }
            }
        }
        ListUDG.EData[] eData = new ListUDG.EData[nearbyWarehouseList.size()];
        Integer v = 0;
        for (NearbyWarehouse nearbyWarehouse : nearbyWarehouseSet) {
            Integer weight = 1;
            List<Orders> orders = ordersService.selectByWarehouseIdAndEnd(nearbyWarehouse.getNearbyWarehouseId(), endCenterWarehouseId);
            if (orders.size() != 0) {
                weight = orders.size();
                //weight*=1000;
            }

            Integer distance = getDistanceByWareHouseId(nearbyWarehouse.getWarehouseId(), nearbyWarehouse.getNearbyWarehouseId()) / 1000;
            eData[v++] = new ListUDG.EData(t1.get(nearbyWarehouse.getWarehouseId()), t1.get(nearbyWarehouse.getNearbyWarehouseId()), distance / weight);
        }
        ListUDG PG = new ListUDG(vemx, eData);
        PG.print();
        PG.DFS();
        char[] bfs = PG.BFS();
        PG.kruskal();
        char[] prim = PG.prim(0);

        String primRoute = "";
        Integer[] primArr = new Integer[5];
        for (int m = 0; m < prim.length; m++) {
            Iterator<Map.Entry<Integer, Character>> three = t1.entrySet().iterator();
            while (three.hasNext()) {
                Map.Entry<Integer, Character> entryThree = three.next();
                if (prim[m] == entryThree.getValue())
                    if (m == prim.length - 1) {
                        primRoute += entryThree.getKey();
                        primArr[m] = entryThree.getKey();
                    } else {
                        primRoute += entryThree.getKey() + "-";
                        primArr[m] = entryThree.getKey();
                    }
                else
                    continue;
            }

        }

        map.put("primRoute", primRoute);
        Integer primSum = 0;
        for (int i = 0; i < primArr.length; i++) {
            List<Orders> orders = ordersService.selectByWarehouseIdAndEnd(primArr[i], endCenterWarehouseId);
            primSum += orders.size();
        }
        map.put("primSum", primSum);
//        map.put("primArr",primArr);


        String bfsRoute = "";
        Integer[] bfsArr = new Integer[5];
        for (int m = 0; m < bfs.length; m++) {
            Iterator<Map.Entry<Integer, Character>> three = t1.entrySet().iterator();
            while (three.hasNext()) {
                Map.Entry<Integer, Character> entryThree = three.next();
                if (bfs[m] == entryThree.getValue())
                    if (m == bfs.length - 1) {
                        bfsRoute += entryThree.getKey();
                        bfsArr[m] = entryThree.getKey();
                    } else {
                        bfsRoute += entryThree.getKey() + "-";
                        bfsArr[m] = entryThree.getKey();
                    }
                else
                    continue;
            }

        }
        map.put("bfsRoute", bfsRoute);
        Integer bfsSum = 0;
        for (int i = 0; i < bfsArr.length; i++) {
            if (bfsArr[i] != endCenterWarehouseId) {
                List<Orders> orders = ordersService.selectByWarehouseIdAndEnd(bfsArr[i], endCenterWarehouseId);
                bfsSum += orders.size();
            } else
                break;

        }
//        map.put("bfsArr",bfsArr);
        map.put("bfsSum", bfsSum);
        return map;
    }


    @Override
    public Integer getDistanceByWareHouseId(Integer startWarehouseId, Integer endWarehouseId) {
        Warehouse startWarehouse = warehouseService.selectById(startWarehouseId);
        Warehouse endWarehouse = warehouseService.selectById(endWarehouseId);
        Map<String, Object> map = new HashMap<>();
        map.put("origin", startWarehouse.getAddress());
        map.put("destination", endWarehouse.getAddress());
        String distance = snCal.getDistance(map);
        return Integer.parseInt(distance);
    }

    @Override
    public Map<String, Object> getEndWarehouseId(Integer startWarehouseId) {
        Map<String, Object> map = new HashMap<>();
        List<NearbyWarehouse> nearbyWarehouseList = nearbyWarehouseService.selectAll();

        Map<Integer, Integer> nearbyWarehouseMap = new HashMap<>();

        for (int i = 0; i < nearbyWarehouseList.size(); i++) {
            nearbyWarehouseMap.put(nearbyWarehouseList.get(i).getWarehouseId(), 1);
            nearbyWarehouseMap.put(nearbyWarehouseList.get(i).getNearbyWarehouseId(), 1);
        }
        nearbyWarehouseMap.put(startWarehouseId, 0);//起点仓库
        map.put("nearWarehouseMap", nearbyWarehouseMap);


        Iterator<Map.Entry<Integer, Integer>> one = nearbyWarehouseMap.entrySet().iterator();
        Integer max = -1;
        Integer maxId = 0;
        Map<String, Object> routeMap = new HashMap<>();
        while (one.hasNext()) {
            Map.Entry<Integer, Integer> entryOne = one.next();
            if (entryOne.getKey() != startWarehouseId) {
                Map<String, Object> route = getRouteByCenterWarehouseToCenterWarehouse(startWarehouseId, entryOne.getKey());
                routeMap.put(entryOne.toString(), route);
                String primSum = route.get("primSum").toString();
                if (max <= Integer.parseInt(primSum)) {
                    max = Integer.parseInt(primSum);
                    maxId = entryOne.getKey();
                }

            }

        }
        map.put("endWarehouseId", maxId);
        map.put("routeMap", routeMap);
        map.put("route", getRouteByCenterWarehouseToCenterWarehouse(startWarehouseId, maxId));
        return map;
    }


}
