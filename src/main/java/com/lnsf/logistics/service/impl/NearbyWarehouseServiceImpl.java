package com.lnsf.logistics.service.impl;

import com.lnsf.logistics.entity.NearbyWarehouse;
import com.lnsf.logistics.entity.Warehouse;
import com.lnsf.logistics.mapper.NearbyWarehouseMapper;
import com.lnsf.logistics.mapper.WarehouseMapper;
import com.lnsf.logistics.service.NearbyWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.lnsf.logistics.Enum.NearbyWarehouseLevel.*;

@Service
@Transactional
public class NearbyWarehouseServiceImpl implements NearbyWarehouseService {

    @Autowired
    private NearbyWarehouseMapper nearbyWarehouseMapper;
    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    public List<NearbyWarehouse> selectAll() {
        return nearbyWarehouseMapper.selectAll();
    }

    @Override
    public List<NearbyWarehouse> selectByWarehouseIdAndLevel(Integer id,Integer level) {
        return nearbyWarehouseMapper.selectByWarehouseIdAndLevel(id,level);
    }

    @Override
    public NearbyWarehouse selectById(Integer id) {
        return nearbyWarehouseMapper.selectById(id);
    }

    @Override
    public NearbyWarehouse selectByWarehouseIdAndOthers(Integer id, Integer others) {
        if (nearbyWarehouseMapper.selectByWarehouseIdAndOthers(id, others) != null) {
            return nearbyWarehouseMapper.selectByWarehouseIdAndOthers(id, others);
        } else return nearbyWarehouseMapper.selectByWarehouseIdAndOthers(others, id);

    }

    @Override
    public String insert(NearbyWarehouse nearbyWarehouse) {
        if (warehouseMapper.selectById(nearbyWarehouse.getWarehouseId()) != null && warehouseMapper.selectById(nearbyWarehouse.getNearbyWarehouseId()) != null) {
            if (nearbyWarehouseMapper.selectByWarehouseIdAndOthers(nearbyWarehouse.getWarehouseId(), nearbyWarehouse.getNearbyWarehouseId()) != null) {
                return "该路线已存在！";
            } else if (nearbyWarehouse.getLevel() == null || !nearbyWarehouse.getLevel().equals(LEVEL_1)|| !nearbyWarehouse.getLevel().equals(LEVEL_2)|| !nearbyWarehouse.getLevel().equals(LEVEL_3)) {
                return "请输入正确的等级！";
            } else if (nearbyWarehouseMapper.insert(nearbyWarehouse)) {
                return "插入成功";
            } else return "插入失败";
        } else return "仓库名有误！";
    }

    @Override
    public String update(NearbyWarehouse nearbyWarehouse) {
        if (nearbyWarehouseMapper.update(nearbyWarehouse)) {
            return "更新成功";
        } else return "更新失败";
    }

    @Override
    public String delete(Integer id) {
        if (nearbyWarehouseMapper.selectById(id).getDelMark().equals(1)) {
            return "该路线已经删除";
        } else if (nearbyWarehouseMapper.delete(id)) {
            return "删除成功";
        } else return "删除失败";
    }
}
