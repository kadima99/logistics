package com.lnsf.logistics.service.impl;


import com.lnsf.logistics.entity.HandoverOrder;
import com.lnsf.logistics.entity.InboundOrder;
import com.lnsf.logistics.entity.Line;
import com.lnsf.logistics.mapper.HandoverOrderMapper;
import com.lnsf.logistics.service.HandoverOrderService;
import com.lnsf.logistics.service.LineService;
import com.lnsf.logistics.service.OutboundOrderService;
import com.lnsf.logistics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lnsf.logistics.Enum.UserPriority.DRIVER;
import static com.lnsf.logistics.Enum.UserStatus.IS_BUSY;

@Service
@Transactional
public class HandoverOrderServiceImpl implements HandoverOrderService {

    @Autowired
    private HandoverOrderMapper handoverOrderMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private LineService lineService;
    @Autowired
    private OutboundOrderService outboundOrderService;

    @Override
    public List<HandoverOrder> selectAll() {
        return handoverOrderMapper.selectAll();
    }

    @Override
    public List<HandoverOrder> selectByHandoverOrderId(Integer id) {
        return handoverOrderMapper.selectByHandoverOrderId(id);
    }

    @Override
    public List<HandoverOrder> selectByUserIdAndStatus(Integer keyword, Integer userId, Integer status) {
        String sql = "SELECT * FROM handover_order WHERE user_id = " + userId +" ";
        if (keyword != null){
            sql += "AND handover_order_id = " + keyword;
        }
        sql += "AND del_mark = " + status + " ";
        return handoverOrderMapper.selectByUserIdAndStatus(sql);

    }

    @Override
    public HandoverOrder selectByOutboundId(Integer id) {
        return handoverOrderMapper.selectByOutboundId(id);
    }

    @Override//获取该司机的历史交接单
    public List<Integer> getHandoverOrderIdByUserId(Integer id,Integer offset){
        return handoverOrderMapper.getHandoverOrderIdByUserId(id,offset);
    }

    @Override
    public Integer countHandoverOrderIdByUserId(Integer id){
        return handoverOrderMapper.countHandoverOrderIdByUserId(id);
    }

    @Override//中心仓库之间
    public Map<String, Object> inboundInsert(Integer userId, Integer[] outboundIds, Integer lineId,Integer level) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userService.selectById(userId) == null) {
            map.put("result", "请检查员工不存在");
        } else if (!userService.selectById(userId).getPriority().equals(2)) {
            map.put("result", "请选择正确的司机");
        } else if (!userService.selectById(userId).getStatus().equals(1)) {
            map.put("result", "请确保司机处于空闲状态");
        }
        Integer flag = 0;
        Integer handoverOrderId = 0;
        String lineSummary = lineService.selectById(lineId).getLineSummary();
        String [] warehouseIds = {};
        if (level.equals(1)){
            System.out.println(lineSummary.substring(2,lineSummary.length()));
            warehouseIds = lineSummary.substring(2,lineSummary.length()).split("-");
        }else warehouseIds = lineSummary.split("-");

        for (int i = 0; i < outboundIds.length; i++) {
            if (flag.equals(0)) {
                if (handoverOrderMapper.insert(new HandoverOrder(userId, outboundIds[i], Integer.parseInt(warehouseIds[i]),1, 0))) {
                    flag++;
                }
                handoverOrderId = handoverOrderMapper.selectByOutboundId(outboundIds[i]).getHandoverOrderId();
                System.out.println(handoverOrderId);
            } else if (handoverOrderMapper.insert(new HandoverOrder(handoverOrderId, userId, outboundIds[i], Integer.parseInt(warehouseIds[i]), 1,0))) {
                flag++;
            }
        }
        if (flag.equals(outboundIds.length)) {
            map.put("result", true);
        } else map.put("result", "插入失败");
        return map;
    }

    @Override//区域内insert
    public Map<String, Object> outboundInsert(Integer userId, Integer[] outboundIds, Integer warehouseId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userService.selectById(userId) == null) {
            map.put("result", "请检查员工不存在");
        } else if (!userService.selectById(userId).getPriority().equals(2)) {
            map.put("result", "请选择正确的司机");
        } else if (!userService.selectById(userId).getStatus().equals(1)) {
            map.put("result", "请确保司机处于空闲状态");
        }
        Integer flag = 0;
        Integer handoverOrderId = 0;
        for (Integer outboundId : outboundIds) {
            if (flag.equals(0)) {
                if (handoverOrderMapper.insert(new HandoverOrder(userId, outboundId, warehouseId, 0,0))) {
                    flag++;
                }
                handoverOrderId = handoverOrderMapper.selectByOutboundId(outboundId).getHandoverOrderId();
                System.out.println(handoverOrderId);
            } else if (handoverOrderMapper.insert(new HandoverOrder(handoverOrderId, userId, outboundId, warehouseId,0,0))) {
                flag++;
            }
        }
        if (flag.equals(outboundIds.length)) {
            map.put("result", true);
        } else map.put("result", "插入失败");
        return map;
    }

    @Override
    public Map<String, Object> update(HandoverOrder handoverOrder) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userService.selectById(handoverOrder.getUserId()) == null) {
            map.put("result", "请检查员工不存在");
        } else if (!userService.selectById(handoverOrder.getUserId()).getPriority().equals(DRIVER.getCode())) {
            map.put("result", "请选择正确的司机");
        } else if (!userService.selectById(handoverOrder.getUserId()).getStatus().equals(IS_BUSY.getCode()) && !selectByHandoverOrderId(handoverOrder.getHandoverOrderId()).get(0).getUserId().equals(handoverOrder.getUserId())) {
            map.put("result", "请确保司机处于空闲状态");
        }else if (handoverOrderMapper.update(handoverOrder)){
            map.put("result",true);
        }else map.put("result","更新失败");
        return map;
    }

    @Override
    public Map<String, Object> delete(HandoverOrder hangoverOrder) {
        return null;
    }
}
