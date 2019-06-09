package com.lnsf.logistics.mapper;

import com.lnsf.logistics.entity.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;

@Mapper
public interface OrdersMapper {

    @Select("${_parameter}")
    List<Orders> selectAllOrderByTime(String sql);

    @Select("SELECT * FROM orders WHERE line_id = #{id} ORDER BY create_date ")
    List<Orders> selectByLineId(Integer id);

    @Select("${_parameter}")
    List<Orders> selectByWarehouseId(String sql);

    @Select("${_parameter}")
    Integer countByWarehouseId(String sql);

    @Select("SELECT * FROM orders WHERE customer_id = #{id} ORDER BY create_date limit #{offset},8")
    List<Orders> selectByCustomerId(Integer id, Integer offset);

    @Select("SELECT count(order_id) FROM orders WHERE customer_id = #{id} ORDER BY create_date")
    Integer countByCustomerId(Integer id);

    @Select("SELECT * FROM orders WHERE warehouse_id = #{warehouseId} AND end_warehouse_id = #{endWarehouseId} AND status = 0 ORDER BY create_date")
    List<Orders> selectByWarehouseIdAndEnd(Integer warehouseId, Integer endWarehouseId);

    @Select("SELECT * FROM orders WHERE order_id = #{id} ")
    Orders selectByOrdersId(Integer id);

    @Insert("INSERT INTO orders VALUES(#{orderId},#{customerId},#{customerName},#{customerPhone},#{customerProvince},#{customerCity},#{customerId},#{customerAddress},#{receiverName},#{receiverPhone},#{receiverProvince},#{receiverCity},#{customer_id},#{receiverAddress},#{wareWeight},#{freight},#{createDate},#{confirmDate},#{lineId},#{status},#{warehouseId},#{endWarehouseId})")
    Boolean insert(Orders orders);

    @Update("UPDATE orders SET customer_id=#{customerId},customer_name=#{customerName},customer_phone=#{customerPhone},customer_province = #{customerProvince},customer_city = #{customerCity},customer_address = #{customerAddress},receiver_name = #{receiverName},receiver_phone = #{receiverPhone}, receiver_province=#{receiverProvince},receiver_city = #{receiverCity},receiver_address = #{receiverAddress},ware_weight = #{wareWeight},freight = #{freight},create_date = #{createDate},confirm_date = #{confirmDate},line_id = #{lineId},status = #{status},warehouse_id = #{warehouseId},end_warehouse_id = #{endWarehouseId} WHERE order_id=#{orderId}")
    Boolean update(Orders orders);

    Boolean delete(Integer id);


}
