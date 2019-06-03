package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.Warehouse;

import java.util.List;

public interface WarehouseService {

    List<Warehouse> selectAll(Integer offset);

    List<Warehouse> selectAllWarehouse();

    List<Warehouse> selectByArea(String area,Integer offset);

    List<Warehouse> selectByLevel(Integer level,Integer offset);

    List<Warehouse> selectByStatus(Integer status,Integer offset);

    Warehouse selectById(Integer id);

    Warehouse selectByUserId(Integer id);

    Warehouse selectByAreaAndLevel(String area, Integer level);

    Warehouse selectByName(String name);

    String insert(Warehouse warehouse);

    String update(Warehouse warehouse);


}
