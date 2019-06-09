package com.lnsf.logistics.mapper;

import com.lnsf.logistics.entity.Car;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CarMapper {


    @Select("SELECT * FROM car LIMIT ")
    List<Car> selectAll();

    @Select("${_parameter}")
    List<Car> selectByWarehouseId(String sql);


    @Select("SELECT * FROM car WHERE car_id = #{id}")
    Car selectById(Integer id);

    @Select("SELECT * FROM car WHERE user_id = #{id}")
    Car selectByUserId(Integer id);

    @Insert("INSERT car VALUES(#{carId},#{userId},#{maxWeight},#{residueWeight},#{status},#{delMark})")
    Boolean insert(Car car);

    @Update("UPDATE car SET user_id = #{userId},max_weight = #{maxWeight},residue_weight = #{residueWeight},status = #{status},del_mark = #{delMark} WHERE car_id = #{carId}")
    Boolean update(Car car);

    @Update("UPDATE car SET del_mark = 1 WHERE car_id = #{id}")
    Boolean delete(Integer id);
}
