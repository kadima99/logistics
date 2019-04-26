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
    public List<User> getAll() {
        return userMapper.getAll();
    }
}
