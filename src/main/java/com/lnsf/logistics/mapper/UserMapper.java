package com.lnsf.logistics.mapper;

import com.lnsf.logistics.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    //mybatis.configuration.mapUnderscoreToCamelCase=true 自动转换下划线成大写

    @Select("SELECT * FROM user LIMIT #{offset},8")
    List<User> selectAll(Integer offset);

    @Select("SELECT * FROM user WHERE priority = #{priority} LIMIT #{offset},8")
    List<User> selectByPriority(Integer priority, Integer offset);//通过权限查找

    @Select("SELECT * FROM user WHERE status = #{status} LIMIT #{offset},8")
    List<User> selectByStatus(Integer status, Integer offset);

    @Select("SELECT * FROM user WHERE warehouse_id = #{warehouseId} LIMIT #{offset},8")
    List<User> selectByWarehouseId(Integer warehouseId, Integer offset);

    @Select("SELECT * FROM user WHERE name = #{name} LIMIT #{offset},8")
    List<User> selectByName(String name, Integer offset);

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
