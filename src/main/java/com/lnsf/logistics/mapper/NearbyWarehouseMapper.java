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


    @Select("SELECT * FROM nearby_warehouse limit #{offset},8")
    List<NearbyWarehouse> selectAll(Integer offset);

    @Select("SELECT * FROM nearby_warehouse WHERE warehouse_id = #{id} OR nearby_warehouse_id = #{id} limit #{offset},8")
    List<NearbyWarehouse> selectByWarehouseId(Integer id, Integer offset);

    @Select("SELECT * FROM nearby_warehouse WHERE id = #{id}")
    NearbyWarehouse selectById(Integer id);

    @Select("SELECT * FROM nearby_warehouse WHERE warehouse_id = #{id} AND nearby_warehouse_id = #{others}")
    NearbyWarehouse selectByWarehouseIdAndOthers(Integer id, Integer others);

    @Select("SELECT * FROM nearby_warehouse WHERE del_mark = #{delMark}")
    NearbyWarehouse selectByDelMark(Integer delMark);

    @Insert("INSERT nearby_warehouse VALUES(#{id},#{warehouseId},#{nearbyWarehouseId},#{distance},#{delMark}) ")
    Boolean insert(NearbyWarehouse nearbyWarehouse);

    @Update("UPDATE nearby_warehouse set distance = #{distance} WHERE id = #{id}")
    Boolean update(NearbyWarehouse nearbyWarehouse);

    @Update("UPDATE nearby_warehouse set del_mark = #{delMark} WHERE id = #{id}")
    Boolean delete(Integer id);


}
