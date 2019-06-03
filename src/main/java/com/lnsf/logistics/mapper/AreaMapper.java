package com.lnsf.logistics.mapper;


import com.lnsf.logistics.entity.Area;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Mapper
public interface AreaMapper {

    @Select("SELECT * FROM area limit #{offset},8")
    List<Area> selectAll(Integer offset);

    @Select("SELECT * FROM area WHERE name = #{name}")
    Area selectByName(String name);

    @Select("SELECT * FROM area WHERE id = #{id}")
    Area selectById(Integer id);

    @Insert("INSET INTO area VALUES(#{id},#{name})")
    Boolean insert(Area area);

    @Update("UPDATE area set name = #{name} WHERE id = #{id}")
    Boolean update(Area area);

    Boolean delete(Integer id);


}
