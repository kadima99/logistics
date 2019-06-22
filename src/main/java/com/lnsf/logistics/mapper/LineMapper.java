package com.lnsf.logistics.mapper;

import com.lnsf.logistics.entity.Line;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface LineMapper {

    @Select("SELECT * FROM line limit ")
    List<Line> selectAll();

    @Select("SELECT * FROM line WHERE begin_id = #{id} ")
    List<Line> selectByBeginId(Integer id);

    @Select("SELECT * FROM line WHERE begin_id = #{beginId} AND end_id = #{endId} ")
    List<Line> selectByBeginIdEndId(Integer beginId, Integer endId);

    @Select("SELECT * FROM line WHERE line_id = #{id}")
    Line selectById(Integer id);

    @Select("SELECT * FROM line WHERE line_summary= #{summary} AND begin_id = #{beginId} AND end_id = #{endId} AND del_mark = 0")
    Line selectByAll(String summary, Integer beginId, Integer endId);

    @Insert("INSERT INTO line VALUES(#{lineId},#{lineSummary},#{beginId},#{endId},#{delMark})")
    Boolean insert(Line line);

    @Update("UPDATE line SET line_summary = #{lineSummary},begin_id = #{beginId},end_id = #{endId},del_mark = #{delMark} WHERE line_id = #{lineId}")
    Boolean update(Line line);

    @Update("UPDATE line SET del_mark = 1 WHERE line_id =#{id}")
    Boolean delete(Integer id);
}
