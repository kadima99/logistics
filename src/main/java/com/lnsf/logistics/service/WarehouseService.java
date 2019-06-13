package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.Warehouse;

import java.util.List;

public interface WarehouseService {

    List<Warehouse> selectAll(String keyword, String city,Integer offset);

    Integer selectAllCountPage(String keyword, String city);

    List<Warehouse> selectAllWarehouseBrief();

    List<Warehouse> selectByArea(Integer area);

    List<Warehouse> selectByLevel(Integer level);

    Warehouse selectById(Integer id);

    Warehouse selectByUserId(Integer id);

    Warehouse selectByAreaAndLevel(Integer area, Integer level);

    Warehouse selectByName(String name);

    String insert(String name, Integer userId, Integer level, Float maxWeight, String address);

    String update(Warehouse warehouse);


}
