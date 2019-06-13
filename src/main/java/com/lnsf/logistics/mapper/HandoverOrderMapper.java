package com.lnsf.logistics.mapper;

import com.lnsf.logistics.entity.HandoverOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HandoverOrderMapper {

    List<HandoverOrder> selectAll();

    List<HandoverOrder> selectByHandoverOrderId(Integer id,Integer offset);

    List<HandoverOrder> selectByUserId(Integer id,Integer offset);

    HandoverOrder selectByOutboundId(Integer id);

    Boolean insert(HandoverOrder handoverOrder);

    Boolean update(HandoverOrder handoverOrder);

    Boolean delete(HandoverOrder hangoverOrder);
}
