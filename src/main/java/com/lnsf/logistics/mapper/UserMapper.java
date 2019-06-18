package com.lnsf.logistics.mapper;

import com.lnsf.logistics.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    //mybatis.configuration.mapUnderscoreToCamelCase=true 自动转换下划线成大写

    @Select("${_parameter}")
    List<User> selectAll(String sql);

    @Select("${_parameter}")
    Integer selectAllCountPage(String sql);

    @Select("SELECT * FROM user WHERE priority = #{priority} ")
    List<User> selectByPriority(Integer priority);//通过权限查找

    @Select("SELECT * FROM user WHERE status = #{status} ")
    List<User> selectByStatus(Integer status);

    @Select("SELECT * FROM user WHERE warehouse_id = #{warehouseId} ")
    List<User> selectByWarehouseId(Integer warehouseId);

    @Select("SELECT * FROM user WHERE warehouse_id = #{warehouseId} AND priority = #{priority}")
    List<User> selectByWarehouseIdAndPriority(Integer warehouseId,Integer priority);

    @Select("SELECT count(*) FROM user WHERE warehouse_id = #{warehouseId} ")
    Integer selectByWarehouseIdCountPage(Integer warehouseId);

    @Select("SELECT * FROM user WHERE user_id = #{id}")
    User selectById(Integer id);

    @Select("SELECT * FROM user WHERE account = #{account}")
    User selectByAccount(String account);

    @Select("SELECT * FROM user WHERE account = #{account} AND password = #{password}")
    User selectByAccountAndPassword(String account, String password);

    @Insert("INSERT user VALUES(#{userId},#{account},#{name},#{password},#{phone},#{priority},#{warehouseId},#{status},#{delMark})")
    Boolean insert(User record);

    @Update("UPDATE user SET account=#{account},name=#{name},password=#{password},phone=#{phone},priority=#{priority},warehouse_id = #{warehouseId},status = #{status},del_mark=#{delMark} WHERE user_id=#{userId}")
    Boolean update(User record);

    @Update("UPDATE user SET del_mark = 1 WHERE user_id = #{id}")
    Boolean deleteById(Integer id);


}
