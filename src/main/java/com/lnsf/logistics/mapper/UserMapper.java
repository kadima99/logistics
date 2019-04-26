package com.lnsf.logistics.mapper;

import com.lnsf.logistics.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user")
    List<User> getAll() ;

}
