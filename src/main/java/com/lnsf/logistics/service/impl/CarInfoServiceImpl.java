package com.lnsf.logistics.service.impl;

import com.lnsf.logistics.entity.CarInfo;
import com.lnsf.logistics.mapper.CarInfoMapper;
import com.lnsf.logistics.mapper.CarMapper;
import com.lnsf.logistics.service.CarInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class CarInfoServiceImpl implements CarInfoService {

    private CarInfoMapper carInfoMapper;
    private CarMapper carMapper;


    @Override
    public List<CarInfo> selectAll(Integer offset) {
        return carInfoMapper.selectAll(offset);
    }

    @Override
    public List<CarInfo> selectByDelMark(Integer delMark, Integer offset) {
        return carInfoMapper.selectByDelMark(delMark, offset);
    }

    @Override
    public List<CarInfo> selectByNextWarehouseId(Integer nextWarehouseId, Integer offset) {
        return carInfoMapper.selectByNextWarehouseId(nextWarehouseId, offset);
    }

    @Override
    public CarInfo selectById(Integer id) {
        return carInfoMapper.selectById(id);
    }

    @Override
    public Boolean updateDelMarkById(Integer id) {
        CarInfo carInfo = selectById(id);
        carInfo.setDelMark(0);
        return carInfoMapper.update(carInfo);
    }

    @Override
    public String insert(CarInfo carInfo) {
        if (carInfoMapper.selectById(carInfo.getCarId()) != null) {
            if (carInfoMapper.insert(carInfo)) {
                return "插入成功";
            } else return "插入失败";
        } else return "该车辆不存在！";
    }

    @Override
    public String update(CarInfo carInfo) {
        if (carInfoMapper.selectById(carInfo.getCarId()) != null) {
            if (carInfoMapper.selectById(carInfo.getCarId()).getDelMark().equals(0)) {
                if (carInfoMapper.update(carInfo)) {
                    return "更新成功";
                } else return "更新失败";
            } else return "该车辆已废弃！不可修改车辆信息";
        } else return "该车辆不存在！";
    }

    @Override
    public String delete(Integer id) {
        if (carInfoMapper.selectById(id) != null) {
            if (carInfoMapper.selectById(id).getHandoverOrderId() != null || carInfoMapper.selectById(id).getNextWarehouseId() != null) {
                return "请确保该车辆不在工作状态再删除！";
            } else if (carInfoMapper.delete(id)) {
                return "删除成功";
            } else return "删除失败";
        } else return "该车辆不存在！";
    }
}
