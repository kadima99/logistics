package com.lnsf.logistics.mapper;

import com.lnsf.logistics.entity.Customer;
import org.apache.ibatis.annotations.*;
import org.attoparser.dom.INestableNode;

import java.util.List;

@Mapper
public interface CustomerMapper {

    @Select("${_parameter}")
    List<Customer> selectAll(String sql);

    @Select("${_parameter}")
    Integer selectAllCountPage(String sql);

    @Select("SELECT * FROM customer where status = #{status} LIMIT #{offset},8")
    List<Customer> selectByStatus(Integer status,Integer offset);

    @Select("SELECT * FROM customer WHERE name = #{name} LIMIT #{offset},8")
    List<Customer> selectByName(String name,Integer offset);

    @Select("SELECT * FROM customer WHERE account = #{account} AND password = #{password}")
    Customer selectByAccountAndPassword(String account,String password);

    @Select("SELECT * FROM customer WHERE account = #{account}")
    Customer selectByAccount(String account);

    @Select("SELECT * FROM customer WHERE phone = #{phone}")
    Customer selectByPhone(String phone);

    @Select("SELECT * FROM customer WHERE customer_id = #{id}")
    Customer selectById(Integer id);

    @Insert("INSERT customer VALUES(#{customerId},#{account},#{name},#{password},#{phone},#{status},#{delMark})")
    Boolean insert(Customer record);

    @Update("UPDATE customer SET account = #{account},name = #{name},password = #{password},phone = #{phone},status = #{status}, del_mark = #{delMark} WHERE customer_id = #{customerId}")
    Boolean update(Customer record);

    @Update("UPDATE customer SET  del_mark = 1 WHERE customer_id = #{customerId}")
    Boolean deleteById(Integer id);


}
