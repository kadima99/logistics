package com.lnsf.logistics.service.impl;

import com.lnsf.logistics.entity.Line;
import com.lnsf.logistics.entity.Warehouse;
import com.lnsf.logistics.mapper.LineMapper;
import com.lnsf.logistics.mapper.WarehouseMapper;
import com.lnsf.logistics.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LineServiceImpl implements LineService {

    @Autowired
    private LineMapper lineMapper;
    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    public List<Line> selectAll() {
        return lineMapper.selectAll();
    }

    @Override
    public List<Line> selectByBeginId(Integer id) {
        return lineMapper.selectByBeginId(id);
    }

    @Override
    public List<Line> selectByBeginIdEndId(Integer beginId, Integer endId) {
        return lineMapper.selectByBeginIdEndId(beginId, endId);
    }

    @Override
    public Line selectById(Integer id) {
        return lineMapper.selectById(id);
    }

    @Override
    public Boolean insert(Line line) {
        return lineMapper.insert(line);
    }

    @Override
    public Boolean update(Line line) {
        return lineMapper.update(line);
    }

    @Override
    public Boolean delete(Integer id) {
        return lineMapper.delete(id);
    }
}
