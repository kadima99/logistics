package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.Locations;

import java.util.List;
import java.util.Map;

public interface LocationsService {

    List<Locations> selectAll();

    Locations selectByName(String name);

    Locations selectById(Integer id);

    List<Locations> selectByParentId(Integer id);

    Locations selectByNameAndParentId(String name,Integer id);

    Map<String,Object> selectLocationsByAddress(String address);

}
