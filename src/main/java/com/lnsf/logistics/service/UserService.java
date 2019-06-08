package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.User;

import java.util.List;

public interface UserService {

    List<User> selectAll(Integer delMark,String keyword,Integer priority,Integer warehouseId,Integer offset);

    List<User> selectByPriority(Integer priority,Integer offset);//通过权限查找

    List<User> selectByStatus(Integer status,Integer offset);

    List<User> selectByWarehouseId(Integer warehouseId, Integer offset);

    List<User> selectByName(String name,Integer offset);

    Integer selectAllCountPage(Integer delMark,String keyword,Integer priority,Integer warehouseId);

    Integer selectByWarehouseIdCountPage(Integer warehouseId);

    Integer selectByStatusCountPage(Integer status);

    User selectById(Integer id);

    User selectByAccount(String account);

    User login(String account,String password);

    Boolean updateDelMarkById (Integer id);

    String insert(User record);

    String update(User record);

    String deleteById(Integer id);
}
