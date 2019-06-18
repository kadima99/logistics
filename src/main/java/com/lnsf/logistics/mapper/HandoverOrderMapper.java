package com.lnsf.logistics.mapper;

import com.lnsf.logistics.entity.HandoverOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface HandoverOrderMapper {

    List<HandoverOrder> selectAll();

    @Select("SELECT * FROM handover_order WHERE handover_order_id=#{id}")
    List<HandoverOrder> selectByHandoverOrderId(Integer id );

    @Select("${_parameter}")
    List<HandoverOrder> selectByUserIdAndStatus(String sql);

    @Select("SELECT * FROM handover_order WHERE outbound_id=#{id}")
    HandoverOrder selectByOutboundId(Integer id);

    @Select("select handover_order_id from handover_order where user_id=#{id} AND del_mark = 1 group by handover_order_id limit #{offset},8")
    List<Integer> getHandoverOrderIdByUserId(Integer id,Integer offset);

    @Select("select count(handover_order_id) from handover_order where user_id=#{id} AND del_mark = 1 group by handover_order_id ")
    Integer countHandoverOrderIdByUserId(Integer id);

    @Insert("INSERT handover_order VALUES(#{handoverOrderId},#{userId},#{outboundId},now(),#{warehouseId},#{delMark})")
    Boolean insert(HandoverOrder handoverOrder);

    @Update("UPDATE handover_order SET user_id=#{userId},outbound_id=#{outboundId},create_date=#{createDate},warehouse_id=#{warehouseId},del_mark=#{delMark} WHERE handover_order_id =#{handoverOrderId}")
    Boolean update(HandoverOrder handoverOrder);

    Boolean delete(HandoverOrder hangoverOrder);
}
