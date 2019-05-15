package com.lnsf.logistics.mapper;

import com.lnsf.logistics.entity.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CustomerMapper {

    @Select("SELECT * FROM customer")
    List<Customer> selectAll();

    @Select("SELECT * FROM customer where status = #{status}")
    List<Customer> selectByStatus(Integer status);

    @Select("SELECT * FROM user WHERE name = #{name} AND password = #{password}")

    Customer selectByNameAndPassword(String name,String password);

    @Select("SELECT * FROM user WHERE name = #{name}")
    Customer selectByName(String name);

    @Insert("INSERT customer VALUES(#{customerId},#{account},#{name},#{password},#{phone},#{delMark})")
    Boolean insert(Customer record);

    @Update("UPDATE customer SET account = #{account},name = #{name},password = #{password},phone = #{phone},del_mark = #{delMark} WHERE customer_id = #{customerId}")
    Boolean update(Customer record);

    @Delete("DELETE FROM customer WHERE customer_id = #{id}")
    Boolean deleteById(Integer id);


}
