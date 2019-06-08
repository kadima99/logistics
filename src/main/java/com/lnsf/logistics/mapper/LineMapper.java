package com.lnsf.logistics.mapper;

import com.lnsf.logistics.entity.Line;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface LineMapper {

    @Select("SELECT * FROM line limit #{offset},8")
    List<Line> selectAll(Integer offset);

    @Select("SELECT * FROM line WHERE begin_id = #{id} limit #{offset},8")
    List<Line> selectByBeginId(Integer id, Integer offset);

    @Select("SELECT * FROM line WHERE begin_id = #{beginId} AND end_id = #{endId} limit #{offset},8")
    List<Line> selectByBeginIdEndId(Integer beginId, Integer endId, Integer offset);

    @Select("SELECT * FROM line WHERE line_id = #{id}")
    Line selectById(Integer id);

    @Insert("INSERT INTO line VALUES(#{lineId},#{lineSummary},#{beginId},#{endId},#{delMark})")
    Boolean insert(Line line);

    @Update("UPDATE line SET line_id = #{lineId},line_summary = #{lineSummary},begin_id = #{beginId},end_id = #{endId},del_mark = #{delMark})")
    Boolean update(Line line);

    @Update("UPDATE line SET del_mark = 1 WHERE line_id =#{id}")
    Boolean delete(Integer id);
}