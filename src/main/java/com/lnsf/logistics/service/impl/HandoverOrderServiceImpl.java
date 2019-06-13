package com.lnsf.logistics.service.impl;


import com.lnsf.logistics.entity.HandoverOrder;
import com.lnsf.logistics.mapper.HandoverOrderMapper;
import com.lnsf.logistics.service.HandoverOrderService;
import com.lnsf.logistics.service.OutboundOrderService;
import com.lnsf.logistics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class HandoverOrderServiceImpl implements HandoverOrderService {

    @Autowired
    private HandoverOrderMapper handoverOrderMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private OutboundOrderService outboundOrderService;

    @Override
    public List<HandoverOrder> selectAll() {
        return handoverOrderMapper.selectAll();
    }

    @Override
    public List<HandoverOrder> selectByHandoverOrderId(Integer id, Integer offset) {
        return handoverOrderMapper.selectByHandoverOrderId(id, offset);
    }

    @Override
    public List<HandoverOrder> selectByUserId(Integer id, Integer offset) {
        return handoverOrderMapper.selectByUserId(id, offset);
    }

    @Override
    public HandoverOrder selectByOutboundId(Integer id) {
        return handoverOrderMapper.selectByOutboundId(id);
    }

    @Override
    public Map<String, Object> insert(Integer userId, Integer[] outboundIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userService.selectById(userId) == null) {
            map.put("result", "请检查员工不存在");
        } else if (!userService.selectById(userId).getPriority().equals(2)) {
            map.put("result", "请选择正确的司机");
        } else if (!userService.selectById(userId).getStatus().equals(1)) {
            map.put("result", "请确保司机处于空闲状态");
        }
        for (Integer outboundId : outboundIds) {
            if (handoverOrderMapper.insert(new HandoverOrder(userId, outboundId, 0))) {
                map.put("result", true);
            } else map.put("result", "插入失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> update(HandoverOrder handoverOrder) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userService.selectById(handoverOrder.getUserId()) == null) {
            map.put("result", "请检查员工不存在");
        } else if (!userService.selectById(handoverOrder.getUserId()).getPriority().equals(2)) {
            map.put("result", "请选择正确的司机");
        } else if (!userService.selectById(handoverOrder.getUserId()).getStatus().equals(1) && ! handoverOrderMapper.selectByHandoverOrderId(handoverOrder.getHandoverOrderId(),0).get(0).getUserId().equals(handoverOrder.getUserId())) {
            map.put("result", "请确保司机处于空闲状态");
        }
        return map;
    }

    @Override
    public Map<String, Object> delete(HandoverOrder hangoverOrder) {
        return null;
    }
}
