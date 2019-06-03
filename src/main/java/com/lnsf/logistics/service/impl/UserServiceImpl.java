package com.lnsf.logistics.service.impl;


import com.lnsf.logistics.entity.User;
import com.lnsf.logistics.mapper.UserMapper;
import com.lnsf.logistics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.lnsf.logistics.Enum.UserStatus.IS_BUSY;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    public UserMapper userMapper;


    @Override
    public List<User> selectAll(Integer offset) {
        return userMapper.selectAll(offset);
    }

    @Override
    public List<User> selectByPriority(Integer priority, Integer offset) {
        return userMapper.selectByPriority(priority, offset);
    }

    @Override
    public List<User> selectByStatus(Integer status, Integer offset) {
        return userMapper.selectByStatus(status, offset);
    }

    @Override
    public List<User> selectByWarehouseId(Integer warehouseId, Integer offset) {
        return userMapper.selectByWarehouseId(warehouseId, offset);
    }

    @Override
    public List<User> selectByName(String name, Integer offset) {
        return userMapper.selectByName(name, offset);
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public String login(String account, String password) {
        if (userMapper.selectByAccount(account) != null) {
            if (userMapper.selectByAccount(account).getDelMark().equals(0)) {
                if (userMapper.selectByAccountAndPassword(account, password) != null) {
                    return "登陆成功";
                } else return "密码错误！";
            } else return "该员工已经离职！";
        } else return "账户不存在！";
    }

    @Override
    public User selectByAccount(String account) {
        return userMapper.selectByAccount(account);
    }

    @Override
    public Boolean updateDelMarkById(Integer id) {
        User user = selectById(id);
        user.setDelMark(0);
        return userMapper.update(user);
    }

    @Override
    public String insert(User record) {
        if (selectByAccount(record.getAccount()) != null) {
            return "该账户已存在！";
        } else if (userMapper.insert(record)) {
            return "插入成功";
        } else return "插入失败";

    }

    @Override
    public String update(User record) {
        if (userMapper.selectById(record.getUserId()).getDelMark().equals(0)) {
            if (userMapper.update(record)) {
                return "修改成功";
            } else return "修改失败";
        } else return "该员工已离职，不可修改！";
    }

    @Override
    public String deleteById(Integer id) {
        if (selectById(id) != null) {
            System.out.println(userMapper.selectById(id).getStatus());
            System.out.println(IS_BUSY.getCode());
            if (userMapper.selectById(id).getDelMark() == 1) {
                return "该用户已经删除！请勿重复操作";
            } else if (userMapper.selectById(id).getStatus().equals(IS_BUSY.getCode())) {
                return "请确保该员工不再工作中再删除！";
            } else if (userMapper.deleteById(id)) {
                return "删除成功";
            } else return "删除失败";
        } else return "id有误！";
    }
}
