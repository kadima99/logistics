package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.User;

import java.util.List;

public interface UserService {

    List<User> selectAll(Integer offset);

    List<User> selectByPriority(Integer priority,Integer offset);//通过权限查找

    List<User> selectByStatus(Integer status,Integer offset);

    User selectById(Integer id);

    List<User> selectByName(String name,Integer offset);

    User selectByAccount(String account);

    String login(String account,String password);

    List<User> selectByWarehouseId(Integer warehouseId, Integer offset);

    Boolean updateDelMarkById (Integer id);

    String insert(User record);

    String update(User record);

    String deleteById(Integer id);
}
