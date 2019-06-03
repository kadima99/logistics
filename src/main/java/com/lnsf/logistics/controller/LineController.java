package com.lnsf.logistics.controller;

import com.lnsf.logistics.entity.Line;
import com.lnsf.logistics.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/line")
public class LineController {

    @Autowired
    private LineService lineService;

    @RequestMapping("/getAll")
    public List<Line> selectAll() {
        Integer page = 1;
        Integer offset = (page - 1) * 8;
        return lineService.selectAll(offset);
    }

    @RequestMapping("/selectByBeginId")
    public List<Line> selectByBeginId() {
        Integer page = 1;
        Integer offset = (page - 1) * 8;
        Integer id = 1;
        return lineService.selectByBeginId(id, offset);
    }

    @RequestMapping("/selectByBeginIdEndId")
    public List<Line> selectByBeginIdEndId() {
        Integer page = 1;
        Integer offset = (page - 1) * 8;
        Integer beginId = 1;
        Integer endId = 3;
        return lineService.selectByBeginIdEndId(beginId, endId, offset);
    }

    @RequestMapping("/selectById")
    public Line selectById() {
        Integer id = 1;
        return lineService.selectById(id);
    }

    public Boolean insert(Line line) {
        return null;
    }

    public Boolean update(Line line) {
        return null;
    }

    public Boolean delete(Integer id) {
        return null;
    }
}
