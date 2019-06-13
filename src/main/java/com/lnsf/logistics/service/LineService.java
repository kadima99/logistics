package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.Line;

import java.util.List;

public interface LineService {

    List<Line> selectAll();

    List<Line> selectByBeginId(Integer id);

    List<Line> selectByBeginIdEndId(Integer beginId, Integer endId);

    Line selectById(Integer id);

    Boolean insert(List<Integer> allWarehouseId,Integer beginId,Integer endId);

    Boolean update(Line line);

    Boolean delete(Integer id);
}
