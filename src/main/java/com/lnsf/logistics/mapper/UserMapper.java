package com.lnsf.logistics.mapper;

import com.lnsf.logistics.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user")
    List<User> selectAll();

    List<User> selectByPriority(Integer priority);//通过权限查找

    List<User> selectByState(Integer state);

    User selectById(Integer id);

    User selectByName(String name);

    User selectByAccountAndPassword(String account,String password);

    Boolean insert(User record);

    Boolean update(User record);

    Boolean deleteById(Integer id);




}
