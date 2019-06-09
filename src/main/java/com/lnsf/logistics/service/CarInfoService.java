package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.Car;
import com.lnsf.logistics.entity.CarInfo;

import java.util.Date;
import java.util.List;

public interface CarInfoService {


    List<CarInfo> selectAll(List<Car> cars);

    CarInfo selectById(Integer id);

    String insert(CarInfo carInfo);

    String update(CarInfo carInfo);

    String delete(Integer id);
}
