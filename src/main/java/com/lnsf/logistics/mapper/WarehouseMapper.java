package com.lnsf.logistics.mapper;

import com.lnsf.logistics.entity.Warehouse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface WarehouseMapper {

    @Select("${_parameter}")
    List<Warehouse> selectAll(String sql);

    @Select("${_parameter}")
    Integer selectAllCountPage(String sql);

    @Select("SELECT * FROM warehouse")
    List<Warehouse> selectAllWarehouseBrief();

    @Select("SELECT * FROM warehouse WHERE area = #{area} ")
    List<Warehouse> selectByArea(Integer area);

    @Select("SELECT * FROM warehouse WHERE level = #{level} ")
    List<Warehouse> selectByLevel(Integer level);

    @Select("SELECT * FROM warehouse WHERE warehouse_id = #{id}")
    Warehouse selectById(Integer id);

    @Select("SELECT * FROM warehouse WHERE user_id = #{id}")
    Warehouse selectByUserId(Integer id);

    @Select("SELECT * FROM warehouse WHERE  area = #{area} AND level = #{level} ")
    Warehouse selectByAreaAndLevel(Integer area, Integer level);

    @Select("SELECT * FROM warehouse WHERE name = #{name}")
    Warehouse selectByName(String name);

    @Insert("INSERT warehouse VALUES(#{warehouseId},#{name},#{address},#{userId},#{area},#{level},#{maxWeight},#{residueWeight},#{status})")
    Boolean insert(Warehouse warehouse);

    @Update("UPDATE warehouse SET name =#{name},address = #{address},user_id = #{userId},area = #{area},level = #{level},max_weight = #{maxWeight},residue_weight = #{residueWeight},status = #{status} WHERE warehouse_id = #{warehouseId}")
    Boolean update(Warehouse warehouse);



}
