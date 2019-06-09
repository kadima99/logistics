package com.lnsf.logistics.service;

import com.lnsf.logistics.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> selectAll(Integer delMark, String keyword, Integer priority, Integer warehouseId, Integer offset);

    List<User> selectByPriority(Integer priority);//通过权限查找

    List<User> selectByStatus(Integer status);

    List<User> selectByWarehouseId(Integer warehouseId);

    Integer selectAllCountPage(Integer delMark, String keyword, Integer priority, Integer warehouseId);

    Integer selectByWarehouseIdCountPage(Integer warehouseId);

    User selectById(Integer id);

    User selectByAccount(String account);

    User login(String account, String password);

    Map<String,Object> resetPassword(List<Long> userId);

    Boolean updateDelMarkById(Integer id);

    String insert(User record);

    String update(User record);

    Map<String, Object> deleteById(List<Long> userId);
}
