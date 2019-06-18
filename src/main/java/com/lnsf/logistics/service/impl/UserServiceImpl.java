package com.lnsf.logistics.service.impl;


import com.lnsf.logistics.entity.User;
import com.lnsf.logistics.mapper.UserMapper;
import com.lnsf.logistics.service.OrdersService;
import com.lnsf.logistics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lnsf.logistics.Enum.UserStatus.IS_BUSY;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    public UserMapper userMapper;


    @Override
    public List<User> selectAll(Integer delMark, String keyword, Integer priority, Integer warehouseId, Integer offset) {
        String sql = "SELECT * FROM user where del_mark = " + delMark + " AND priority != 1";
        if (!keyword.equals("")) {
            sql += " AND (name like \"%" + keyword + "%\" or account like  \"%" + keyword + "%\")";
        }
        if (priority != null) {
            sql += " AND priority = " + priority;
        }
        if (warehouseId != null) {
            sql += " AND warehouse_id = " + warehouseId;
        }
        if (offset != null) {
            sql += " LIMIT " + offset + ",8";
        }
        return userMapper.selectAll(sql);
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
    public List<User> selectByWarehouseId(Integer warehouseId) {
        return userMapper.selectByWarehouseId(warehouseId);
    }

    @Override
    public Integer selectAllCountPage(Integer delMark, String keyword, Integer priority, Integer warehouseId) {
        String sql = "SELECT count(user_id) FROM user where del_mark = " + delMark;
        if (!keyword.equals("")) {
            sql += " AND (name like \"%" + keyword + "%\" or account like  \"%" + keyword + "%\")";
        }
        if (priority != null) {
            sql += " AND priority = " + priority;
        }
        if (warehouseId != null) {
            sql += " AND warehouse_id = " + warehouseId;
        }
        return userMapper.selectAllCountPage(sql);
    }

    @Override
    public Integer selectByWarehouseIdCountPage(Integer warehouseId) {
        return userMapper.selectByWarehouseIdCountPage(warehouseId);
    }


    @Override
    public List<User> selectByWarehouseIdAndPriority(Integer warehouseId, Integer priority) {
        return userMapper.selectByWarehouseIdAndPriority(warehouseId, priority);
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public User login(String account, String password) {
        return userMapper.selectByAccountAndPassword(account, password);
    }

    @Override
    public User selectByAccount(String account) {
        return userMapper.selectByAccount(account);
    }

    @Override
    public Map<String, Object> resetPassword(List<Long> userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < userId.size(); i++) {
            Integer id = userId.get(i).intValue();
            User user = userMapper.selectById(id);
            user.setPassword("123456");
            if (userMapper.update(user)) {
                System.out.println("come in");
                map.put("result", true);
            } else map.put("result", false);
        }
        return map;
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
    public Map<String, Object> deleteById(List<Long> userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < userId.size(); i++) {
            Integer id = userId.get(i).intValue();
            if (selectById(id) != null) {
                if (userMapper.selectById(id).getDelMark() == 1) {
                    map.put("result", "该用户已经删除！请勿重复操作");
                } else if (userMapper.selectById(id).getStatus().equals(IS_BUSY.getCode())) {
                    map.put("result", "请确保该员工不再工作中再删除");
                } else if (userMapper.deleteById(id)) {
                    map.put("result", true);
                } else map.put("result", "删除失败");
            } else map.put("result", "id有误");
            if (userMapper.deleteById(id).equals("删除成功")) {
                map.put("result", true);
            } else map.put("result", userMapper.deleteById(id));
        }

        return map;
    }
}
