package com.lnsf.logistics.entity.logistics.service.impl;


import com.lnsf.logistics.entity.logistics.entity.User;
import com.lnsf.logistics.entity.logistics.mapper.UserMapper;
import com.lnsf.logistics.entity.logistics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    public UserMapper userMapper;


    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public List<User> selectByPriority(Integer priority) {
        return userMapper.selectByPriority(priority);
    }

    @Override
    public List<User> selectByStatus(Integer status) {
        return userMapper.selectByStatus(status);
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public User selectByName(String name) {
        return userMapper.selectByName(name);
    }

    @Override
    public User selectByAccountAndPassword(String account, String password) {
        return userMapper.selectByAccountAndPassword(account, password);
    }

    @Override
    public Boolean insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public Boolean update(User record) {
        return userMapper.update(record);
    }

    @Override
    public Boolean deleteById(Integer id) {
        return userMapper.deleteById(id);
    }
}
