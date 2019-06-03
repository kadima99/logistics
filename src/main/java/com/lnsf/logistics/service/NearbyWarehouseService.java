package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.NearbyWarehouse;

import java.util.List;

public interface NearbyWarehouseService {

    List<NearbyWarehouse> selectAll(Integer offset);

    List<NearbyWarehouse> selectByWarehouseId(Integer id, Integer offset);

    NearbyWarehouse selectById(Integer id);

    NearbyWarehouse selectByWarehouseIdAndOthers(Integer id, Integer others);

    NearbyWarehouse selectByDelMark(Integer delMark);

    String insert(NearbyWarehouse nearbyWarehouse);

    String update(NearbyWarehouse nearbyWarehouse);

    String delete(Integer id);
}
