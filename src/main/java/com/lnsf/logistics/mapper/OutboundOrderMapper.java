package com.lnsf.logistics.mapper;

import com.lnsf.logistics.entity.InboundOrder;
import com.lnsf.logistics.entity.OutboundOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OutboundOrderMapper {

    @Select("SELECT * FROM outbound_order ORDER BY create_date DESC ")
    List<OutboundOrder> selectAll();

    @Select("SELECT * FROM outbound_order WHERE order_id = #{id} ORDER BY create_date ")
    List<OutboundOrder> selectByOrderId(Integer id);

    @Select("SELECT * FROM outbound_order WHERE warehouse_id = #{id} ORDER BY create_date DESC LIMIT #{offset},8")
    List<OutboundOrder> selectByWarehouseId(Integer id,Integer offset);

    @Select("SELECT count(outbound_order_id) FROM outbound_order WHERE warehouse_id = #{id}")
    Integer countByWarehouseId(Integer id);

    @Select("SELECT * FROM outbound_order WHERE outbound_order_id = #{id}")
    List<OutboundOrder> selectById(Integer id);

    @Select("SELECT * FROM outbound_order WHERE order_id = #{id} AND del_mark = #{delMark}")
    OutboundOrder selectByOrderIdAnd(Integer id,Integer delMark);

    @Select("SELECT outbound_order_id from outbound_order WHERE warehouse_id = #{id} group by outbound_order_id ")
    List<Integer> getOutboundOrderIdByWarehouseId(Integer id,Integer offset);

    @Select("select count(outbound_order_id) from (SELECT outbound_order_id from outbound_order WHERE warehouse_id = #{id} group by outbound_order_id) b")
    Integer countOutboundOrderIdByWarehouseId(Integer id);

    @Insert("INSERT outbound_order VALUES(#{outboundOrderId},#{warehouseId},#{nextWarehouseId},#{orderId},now(),#{delMark})")
    Boolean insert(OutboundOrder inboundOrder);

    @Update("UPDATE outbound_order set order_id = #{orderId},warehouse_id= #{warehouseId},next_warehouse_id = #{nextWarehouseId},del_mark = #{delMark},create_date = #{createDate} WHERE outbound_order_id = #{outboundOrderId}")
    Boolean update(OutboundOrder inboundOrder);

    @Update("UPDATE outbound_order set del_mark = 1 WHERE outbound_order_id = #{id}")
    Boolean delete(Integer id);


}
