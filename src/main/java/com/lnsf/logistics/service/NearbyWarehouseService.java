package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.NearbyWarehouse;

import java.util.List;

public interface NearbyWarehouseService {

    List<NearbyWarehouse> selectAll();

    List<NearbyWarehouse> selectByWarehouseIdAndLevel(Integer id,Integer level);

    NearbyWarehouse selectById(Integer id);

    NearbyWarehouse selectByWarehouseIdAndOthers(Integer id, Integer others);

    String insert(NearbyWarehouse nearbyWarehouse);

    String update(NearbyWarehouse nearbyWarehouse);

    String delete(Integer id);
}
