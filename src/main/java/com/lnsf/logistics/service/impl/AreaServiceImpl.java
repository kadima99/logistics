package com.lnsf.logistics.service.impl;

import com.lnsf.logistics.entity.Area;
import com.lnsf.logistics.mapper.AreaMapper;
import com.lnsf.logistics.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Area> selectAll(Integer offset) {
        return areaMapper.selectAll(offset);
    }

    @Override
    public Area selectByName(String name) {
        return areaMapper.selectByName(name);
    }

    @Override
    public Area selectById(Integer id) {
        return areaMapper.selectById(id);
    }

    @Override
    public Boolean insert(Area area) {
        return areaMapper.insert(area);
    }

    @Override
    public Boolean update(Area area) {
        return areaMapper.update(area);
    }

    @Override
    public Boolean delete(Integer id) {
        return areaMapper.delete(id);
    }
}
