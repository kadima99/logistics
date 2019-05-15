package com.lnsf.logistics.entity.logistics.mapper;

import com.lnsf.logistics.entity.logistics.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user")
    List<User> selectAll();

    @Select("SELECT * FROM user WHERE priority = #{priority}")
    List<User> selectByPriority(Integer priority);//通过权限查找

    @Select("SELECT * FROM user WHERE status = #{status}")
    List<User> selectByStatus(Integer status);

    @Select("SELECT * FROM user WHERE user_id = #{id}")
    User selectById(Integer id);

    @Select("SELECT * FROM user WHERE name = #{name}")
    User selectByName(String name);

    @Select("SELECT * FROM user WHERE account = #{account} AND password = #{password}")
    User selectByAccountAndPassword(String account, String password);

    @Insert("INSERT user VALUES(#{userId},#{account},#{name},#{password},#{phone},#{priority},#{warehouseId},#{status},#{delMark})")
    Boolean insert(User record);

    @Update("UPDATE user SET account=#{account},name=#{name},password=#{password},phone=#{phone},priority=#{priority},del_mark=#{delMark} WHERE user_id=#{userId}")
    Boolean update(User record);

    @Delete("DELETE FROM user WHERE user_id = #{id}")
    Boolean deleteById(Integer id);




}
