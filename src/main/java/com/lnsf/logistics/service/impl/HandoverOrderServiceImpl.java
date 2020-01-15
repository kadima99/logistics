package com.lnsf.logistics.service.impl;


import com.lnsf.logistics.entity.HandoverOrder;
import com.lnsf.logistics.entity.InboundOrder;
import com.lnsf.logistics.entity.Line;
import com.lnsf.logistics.mapper.HandoverOrderMapper;
import com.lnsf.logistics.service.HandoverOrderService;
import com.lnsf.logistics.service.LineService;
import com.lnsf.logistics.service.OutboundOrderService;
import com.lnsf.logistics.service.UserService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.*;

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

    @Override//区域之间送件装车
    public Map<String, Object> inboundInsert(Integer userId, Integer[] outboundIds, Integer lineId) {
        System.out.println("inbound");
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
        String[] warehouseIds = lineSummary.split("-");

        selectByOutboundId(outboundIds.length);
        for (int i = 0; i < outboundIds.length; i++) {
            Integer warehouseId = outboundOrderService.selectById(outboundIds[i]).get(0).getNextWarehouseId();
            if (flag.equals(0)) {
                if (handoverOrderMapper.insert(new HandoverOrder(userId, outboundIds[i], warehouseId,0, 0))) {
                    flag++;
                }
                handoverOrderId = handoverOrderMapper.selectByOutboundId(outboundIds[i]).getHandoverOrderId();
            } else if (handoverOrderMapper.insert(new HandoverOrder(handoverOrderId, userId, outboundIds[i], warehouseId, 0,0))) {
                flag++;
            }
        }
        if (flag.equals(outboundIds.length)) {
            map.put("result", true);
        } else map.put("result", "插入失败");
        return map;
    }

    @Override//中心仓库之间送件装车
    public Map<String, Object> centerInboundInsert(Integer userId, Integer[] outboundIds, Integer lineId) {
        System.out.println("inbound");
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
        String[] warehouseIds = lineSummary.split("-");

        for (int i = 0; i < outboundIds.length; i++) {
            Integer warehouseId = Integer.parseInt(warehouseIds[warehouseIds.length-1]);
            if (flag.equals(0)) {
                if (handoverOrderMapper.insert(new HandoverOrder(userId, outboundIds[i], warehouseId,0, 0))) {
                    flag++;
                }
                handoverOrderId = handoverOrderMapper.selectByOutboundId(outboundIds[i]).getHandoverOrderId();
            } else if (handoverOrderMapper.insert(new HandoverOrder(handoverOrderId, userId, outboundIds[i], warehouseId, 0,0))) {
                flag++;
            }
        }
        if (flag.equals(outboundIds.length)) {
            map.put("result", true);
        } else map.put("result", "插入失败");
        return map;
    }

    @Override//区域内揽件
    public Map<String, Object> outboundInsert(Integer userId, Integer[] outboundIds, Integer warehouseId) {
        System.out.println("outbound");
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
                if (handoverOrderMapper.insert(new HandoverOrder(userId, outboundId, warehouseId, 1,0))) {
                    flag++;
                }
                handoverOrderId = handoverOrderMapper.selectByOutboundId(outboundId).getHandoverOrderId();
            } else if (handoverOrderMapper.insert(new HandoverOrder(handoverOrderId, userId, outboundId, warehouseId,1,0))) {
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
