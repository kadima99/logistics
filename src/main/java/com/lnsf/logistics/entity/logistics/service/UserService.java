package com.lnsf.logistics.entity.logistics.service;

import com.lnsf.logistics.entity.logistics.entity.User;

import java.util.List;

public interface UserService {

    List<User> selectAll();

    List<User> selectByPriority(Integer priority);//通过权限查找

    List<User> selectByStatus(Integer status);

    User selectById(Integer id);

    User selectByName(String name);

    User selectByAccountAndPassword(String account, String password);

    Boolean insert(User record);

    Boolean update(User record);

    Boolean deleteById(Integer id);
}
