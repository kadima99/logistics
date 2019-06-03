package com.lnsf.logistics.mapper;

import com.lnsf.logistics.entity.Customer;
import org.apache.ibatis.annotations.*;
import org.attoparser.dom.INestableNode;

import java.util.List;

@Mapper
public interface CustomerMapper {

    @Select("SELECT * FROM customer LIMIT #{offset},8")
    List<Customer> selectAll(Integer offset);

    @Select("SELECT * FROM customer where status = #{status} LIMIT #{offset},8")
    List<Customer> selectByStatus(Integer status,Integer offset);

    @Select("SELECT * FROM customer WHERE name = #{name} LIMIT #{offset},8")
    List<Customer> selectByName(String name,Integer offset);

    @Select("SELECT * FROM customer WHERE account = #{account} AND password = #{password}")
    Customer selectByAccountAndPassword(String account,String password);

    @Select("SELECT * FROM customer WHERE account = #{account}")
    Customer selectByAccount(String account);

    @Select("SELECT * FROM customer WHERE customer_id = #{id}")
    Customer selectById(Integer id);

    @Insert("INSERT customer VALUES(#{customerId},#{account},#{name},#{password},#{phone},#{status})")
    Boolean insert(Customer record);

    @Update("UPDATE customer SET account = #{account},name = #{name},password = #{password},phone = #{phone},status = #{status} WHERE customer_id = #{customerId}")
    Boolean update(Customer record);

    @Delete("DELETE FROM customer WHERE customer_id = #{id}")
    Boolean deleteById(Integer id);


}
