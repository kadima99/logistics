package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.Line;
import com.lnsf.logistics.route.RouteWeight;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface LineService {

    List<Line> selectAll();

    List<Line> selectByBeginId(Integer id);

    List<Line> selectByBeginIdEndId(Integer beginId, Integer endId);

    Line selectByAll(String summary,Integer beginId, Integer endId);

    Line selectById(Integer id);

    Boolean insert(Line line);

    Boolean update(Line line);

    Boolean delete(Integer id);

    Map<String,Object> getRouteByCenterWarehouse(Integer centerWarehouseId);

    Map<String,Object> getRouteByCenterWarehouseToCenterWarehouse(Integer startCenterWarehouseId,Integer endCenterWarehouseId);

    Integer getDistanceByWareHouseId(Integer startWarehouseId,Integer endWarehouseId);

    Map<String,Object> getEndWarehouseId(Integer startWarehouseId);
}
