package com.lnsf.logistics.mapper;


import com.lnsf.logistics.entity.NearbyWarehouse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface NearbyWarehouseMapper {


    @Select("SELECT * FROM nearby_warehouse WHERE del_mark = 0")
    List<NearbyWarehouse> selectAll();

    @Select("SELECT * FROM nearby_warehouse WHERE warehouse_id = #{id} AND level = #{level} AND del_mark = 0")
    List<NearbyWarehouse> selectByWarehouseIdAndLevel(Integer id,Integer level);

    @Select("SELECT * FROM nearby_warehouse WHERE id = #{id}")
    NearbyWarehouse selectById(Integer id);

    @Select("SELECT * FROM nearby_warehouse WHERE warehouse_id = #{id} AND nearby_warehouse_id = #{others} AND del_mark = 0")
    NearbyWarehouse selectByWarehouseIdAndOthers(Integer id, Integer others);

    @Insert("INSERT nearby_warehouse VALUES(#{id},#{warehouseId},#{nearbyWarehouseId},#{level},#{delMark}) ")
    Boolean insert(NearbyWarehouse nearbyWarehouse);

    @Update("UPDATE nearby_warehouse set warehouse_id = #{warehouseId},nearby_warehouse_id = #{nearbyWarehouseId},level = #{level},distance = #{distance} WHERE id = #{id}")
    Boolean update(NearbyWarehouse nearbyWarehouse);

    @Update("UPDATE nearby_warehouse set del_mark = #{delMark} WHERE id = #{id}")
    Boolean delete(Integer id);


}
