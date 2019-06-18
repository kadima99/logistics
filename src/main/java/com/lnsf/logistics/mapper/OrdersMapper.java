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

    @Select("${_parameter}")
    List<Orders> selectByCustomerId(String sql);

    @Select("${_parameter}")
    Integer countByCustomerId(String sql);

    @Select("${_parameter}")
    List<Orders> selectByReceiverPhone(String sql);

    @Select("${_parameter}")
    Integer countByReceiverPhone(String sql);

    @Select("${_parameter}")
    List<Orders> selectByWarehouseIdAndEnd(String sql);

    @Select("${_parameter}")
    List<Orders> selectByUserIdAndOrderStatus(String sql);

    @Select("${_parameter}")
    Integer countByUserIdAndOrderStatus(String sql);

    @Select("SELECT * FROM orders WHERE order_id = #{id} ")
    Orders selectByOrdersId(Integer id);

    @Select("${_parameter}")
    List<Orders> getDelivery(String sql);

    @Select("${_parameter}")
    Integer countByGetDelivery(String sql);

    @Insert("INSERT INTO orders VALUES(#{orderId},#{customerId},#{customerName},#{customerPhone},#{customerProvince},#{customerCity},#{customerAddress},#{receiverName},#{receiverPhone},#{receiverProvince},#{receiverCity},#{receiverAddress},#{wareWeight},#{freight},now(),#{confirmDate},#{status},#{warehouseId},#{endWarehouseId},#{userId})")
    Boolean insert(Orders orders);

    @Update("UPDATE orders SET customer_id=#{customerId},customer_name=#{customerName},customer_phone=#{customerPhone},customer_province = #{customerProvince},customer_city = #{customerCity},customer_address = #{customerAddress},receiver_name = #{receiverName},receiver_phone = #{receiverPhone}, receiver_province=#{receiverProvince},receiver_city = #{receiverCity},receiver_address = #{receiverAddress},ware_weight = #{wareWeight},freight = #{freight},create_date = #{createDate},confirm_date = #{confirmDate},status = #{status},warehouse_id = #{warehouseId},end_warehouse_id = #{endWarehouseId},user_id =#{userId} WHERE order_id=#{orderId}")
    Boolean update(Orders orders);

    Boolean delete(Integer id);


}
