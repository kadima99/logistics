package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.Car;

import org.springframework.stereotype.Service;

import java.util.List;


public interface CarService {

    List<Car> selectAll(Integer offset);

    List<Car> selectByStatus(Integer status,Integer offset);

    List<Car> selectByDelMark(Integer delMark,Integer offset);

    Car selectById(Integer id);

    Boolean updateDelMarkById (Integer id);

    String insert(Car car);

    String update(Car car);

    String delete(Integer id);
}
