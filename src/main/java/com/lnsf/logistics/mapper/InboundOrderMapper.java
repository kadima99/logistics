package com.lnsf.logistics.mapper;


import com.lnsf.logistics.entity.InboundOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface InboundOrderMapper {

    @Select("SELECT * FROM inbound_order ORDER BY create_date DESC ")
    List<InboundOrder> selectAll();

    @Select("SELECT * FROM inbound_order WHERE order_id = #{id} ORDER BY create_date")
    List<InboundOrder> selectByOrderId(Integer id);

    @Select("SELECT * FROM inbound_order WHERE warehouse_id = #{id} ORDER BY create_date DESC LIMIT #{offset},8")
    List<InboundOrder> selectByWarehouseId(Integer id,Integer offset);

    @Select("SELECT count(inbound_order_id) FROM inbound_order WHERE warehouse_id = #{id}")
    Integer countByWarehouseId(Integer id);

    @Select("SELECT * FROM inbound_order WHERE inbound_order_id = #{id}")
    List<InboundOrder> selectById(Integer id);

    @Select("SELECT inbound_order_id from inbound_order WHERE warehouse_id = #{id} group by inbound_order_id LIMIT #{offset},8")
    List<Integer> getInboundOrderIdByWarehouseId(Integer id,Integer offset);

    @Select("SELECT count(inbound_order_id) from inbound_order WHERE warehouse_id = #{id} group by inbound_order_id ")
    Integer countInboundOrderIdByWarehouseId(Integer id);

    @Insert("INSERT inbound_order VALUES(#{inboundOrderId},#{orderId},#{warehouseId},now(),#{delMark})")
    Boolean insert(InboundOrder inboundOrder);

    @Update("UPDATE inbound_order set order_id = #{orderId},warehouse_id= #{warehouseId},del_mark = #{delMark},create_date = #{createDate} WHERE inbound_order_id = #{id}")
    Boolean update(InboundOrder inboundOrder);

    @Update("UPDATE inbound_order set del_mark = 1 WHERE inbound_order_id = #{id}")
    Boolean delete(Integer id);


}
