package com.lnsf.logistics.service.impl;


import com.lnsf.logistics.entity.User;
import com.lnsf.logistics.mapper.UserMapper;
import com.lnsf.logistics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public List<User> selectByPriority(Integer priority) {
        return userMapper.selectByPriority(priority);
    }

    @Override
    public List<User> selectByState(Integer state) {
        return userMapper.selectByState(state);
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public User selectByNameAndPassword(String username, String password) {
        return userMapper.selectByNameAndPassword(username, password);
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
        return deleteById(id);
    }
}
