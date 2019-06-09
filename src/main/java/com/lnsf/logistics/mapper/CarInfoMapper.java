package com.lnsf.logistics.mapper;

import com.lnsf.logistics.entity.Car;
import com.lnsf.logistics.entity.CarInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

import java.util.Date;
import java.util.List;

@Mapper
public interface CarInfoMapper {

    @Select("SELECT * FROM car_info ")
    List<CarInfo> selectAll();

    @Select("SELECT * FROM car_info WHERE car_id = #{id}")
    CarInfo selectById(Integer id);

    @Insert("INSERT INTO car_info VALUES(#{carId},#{handoverOrderId},#{nextWarehouseId},#{waitTime},#{delMark})")
    Boolean insert(CarInfo carInfo);

    @Update("UPDATE car_info SET handover_order_id=#{handoverOrderId},next_warehouse_id=#{nextWarehouseId},wait_time=#{waitTime},del_mark = #{delMark} WHERE car_id = #{carId}")
    Boolean update(CarInfo carInfo);

    @Update("UPDATE car_info SET del_mark = 1 WHERE car_id = #{id}")
    Boolean delete(Integer id);


}
