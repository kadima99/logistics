package com.lnsf.logistics.mapper;

import com.lnsf.logistics.entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CustomerMapper {

    @Select("SELECT * FROM customer")
    List<Customer> selectAll();

    List<Customer> selectByState(Integer state);

    Customer selectByNameAndPassword(String name,String password);

    Customer selectByName(String name);

    Boolean insert(Customer record);

    Boolean update(Customer record);

    Boolean deleteById(Integer id);


}
