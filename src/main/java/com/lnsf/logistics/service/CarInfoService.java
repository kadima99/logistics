package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.CarInfo;

import java.util.Date;
import java.util.List;

public interface CarInfoService {


    List<CarInfo> selectAll(Integer offset);

    List<CarInfo> selectByDelMark(Integer delMark,Integer offset);

    List<CarInfo> selectByNextWarehouseId(Integer nextWarehouseId,Integer offset);

    CarInfo selectById(Integer id);

    Boolean updateDelMarkById(Integer id);

    String insert(CarInfo carInfo);

    String update(CarInfo carInfo);

    String delete(Integer id);
}
