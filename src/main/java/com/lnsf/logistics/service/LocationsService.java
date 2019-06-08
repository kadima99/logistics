package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.Locations;

import java.util.List;

public interface LocationsService {

    List<Locations> selectAll();

    Locations selectByName(String name);

    Locations selectById(Integer id);

    Locations selectLocationsByAddress(String address);

}
