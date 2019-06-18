package com.lnsf.logistics.mapper;


import com.lnsf.logistics.entity.Locations;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Mapper
public interface LocationsMapper {

    @Select("SELECT * FROM locations")
    List<Locations> selectAll();

    @Select("SELECT * FROM locations WHERE parent_id = #{id}")
    List<Locations> selectByParentId(Integer id);

    @Select("SELECT * FROM locations WHERE name = #{name}")
    Locations selectByName(String name);

    @Select("SELECT * FROM locations WHERE name = #{name} AND parent_id = #{parentId}")
    Locations selectByNameAndParentId(String name,Integer parentId);

    @Select("SELECT * FROM locations WHERE id = #{id}")
    Locations selectById(Integer id);

}
