package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.Line;

import java.util.List;

public interface LineService {

    List<Line> selectAll(Integer offset);

    List<Line> selectByBeginId(Integer id, Integer offset);

    List<Line> selectByBeginIdEndId(Integer beginId, Integer endId, Integer offset);

    Line selectById(Integer id);

    Boolean insert(Line line);

    Boolean update(Line line);

    Boolean delete(Integer id);
}
