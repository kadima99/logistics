package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.Area;

import java.util.List;

public interface AreaService {

    List<Area> selectAll(Integer offset);

    Area selectByName(String name);

    Area selectById(Integer id);

    Boolean insert(Area area);

    Boolean update(Area area);

    Boolean delete(Integer id);
}
