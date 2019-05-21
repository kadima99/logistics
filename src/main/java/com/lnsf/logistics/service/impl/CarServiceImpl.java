package com.lnsf.logistics.service.impl;

import com.lnsf.logistics.entity.Car;
import com.lnsf.logistics.entity.CarInfo;
import com.lnsf.logistics.mapper.CarMapper;
import com.lnsf.logistics.mapper.UserMapper;
import com.lnsf.logistics.service.CarInfoService;
import com.lnsf.logistics.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CarInfoService carInfoService;

    @Override
    public List<Car> selectAll(Integer offset) {
        return carMapper.selectAll(offset);
    }

    @Override
    public List<Car> selectByStatus(Integer status, Integer offset) {
        return carMapper.selectByStatus(status, offset);
    }

    public List<Car> selectByDelMark(Integer delMark,Integer offset) {
        return carMapper.selectByDelMark(delMark,offset);
    }

    @Override
    public Car selectById(Integer id) {
        return carMapper.selectById(id);
    }

    @Override
    public Boolean updateDelMarkById(Integer id) {
        Car car = carMapper.selectById(id);
        car.setDelMark(0);
        carInfoService.updateDelMarkById(id);
        return carMapper.update(car);
    }

    @Override
    public String insert(Car car) {
        if (userMapper.selectById(car.getUserId()) != null) {
            return "该司机已经分配车辆了！";
        } else if (car.getMaxWeight() == null) {
            return "车辆最大重量不能为空！";
        } else if (carMapper.insert(car)) {
            CarInfo carInfo = new CarInfo();
            carInfo.setCarId(car.getUserId());
            carInfoService.insert(carInfo);
            return "插入成功";
        } else return "插入失败";

    }

    @Override
    public String update(Car car) {
        if (carMapper.selectById(car.getCarId()) != null) {
            if (carMapper.selectById(car.getCarId()).getDelMark().equals(0)) {
                if (userMapper.selectById(car.getUserId()) != null) {
                    return "该司机已经分配车辆了！";
                } else if (car.getMaxWeight() == null) {
                    return "车辆最大重量不能为空！";
                } else if (car.getResidueWeight() > car.getMaxWeight()) {
                    return "车辆载量不能超过最大载量！";
                } else if (carMapper.update(car)) {
                    return "更新成功";
                } else return "更新失败";
            } else return "该车辆已经废弃！";
        }
        return "找不到该车辆！";

    }

    @Override
    public String delete(Integer id) {
        if (carMapper.selectById(id) != null) {
            if (carMapper.selectById(id).getDelMark() == 1) {
                return "该车辆已经删除！请勿重复操作";
            } else if (carMapper.selectById(id).getStatus() == 1) {
                return "请确保该车辆不在工作状态再删除！";
            } else if (carMapper.delete(id)) {
                carInfoService.delete(id);
                return "删除成功";
            } else return "删除失败";
        } else return "找不到该车辆！";
    }
}
