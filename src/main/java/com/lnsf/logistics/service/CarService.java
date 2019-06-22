package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.Car;

import org.springframework.stereotype.Service;

import java.util.List;


public interface CarService {

    List<Car> selectAll();

    List<Car> selectByWarehouseId(Integer keyword,Integer id,Integer level,Integer status,Integer offset);

    Integer countByWarehouseId(Integer keyword,Integer id, Integer level, Integer status);

    Car selectById(Integer id);

    Car selectByUserId(Integer id);

    String insert(Car car);

    String update(Car car);

    String delete(Integer id);
}
