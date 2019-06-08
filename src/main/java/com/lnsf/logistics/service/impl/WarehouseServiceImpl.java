package com.lnsf.logistics.service.impl;


import com.lnsf.logistics.entity.Warehouse;
import com.lnsf.logistics.mapper.LocationsMapper;
import com.lnsf.logistics.mapper.UserMapper;
import com.lnsf.logistics.mapper.WarehouseMapper;
import com.lnsf.logistics.service.LocationsService;
import com.lnsf.logistics.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.lnsf.logistics.Enum.WarehouseStatus.IS_FULL;
import static com.lnsf.logistics.Enum.WarehouseStatus.WILL_FULL;

@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private LocationsService locationsService;
    @Autowired
    private UserMapper userMapper;

    public List<Warehouse> selectAll(String keyword, String city, Integer offset) {
        String sql = "SELECT * FROM warehouse WHERE 1=1 ";
        if (keyword == null) {
            keyword = "";
        }
        if (city == null) {
            city = "";
        }
        if (!keyword.equals("")) {
            sql += " AND (name like \"%" + keyword + "%\" or warehouse_id like  \"%" + keyword + "%\")";
        }
        if (!city.equals("")) {
            sql += " AND area like \"%" + city + "%\" ";
        }
        if (offset != null) {
            sql += " LIMIT " + offset + ",8";
        }
        return warehouseMapper.selectAll(sql);
    }

    public Integer selectAllCountPage(String keyword, String city) {
        String sql = "SELECT count(warehouse_id) FROM warehouse ";
        if (keyword == null) {
            keyword = "";
        }
        if (city == null) {
            city = "";
        }
        if (!keyword.equals("") || !city.equals("")) {
            sql += " where ";

            if (!keyword.equals("")) {
                sql += " (name like \"%" + keyword + "%\" or warehouse_id like  \"%" + keyword + "%\")";
            }

            if (!city.equals("")) {
                if (!keyword.equals("")) {
                    sql += " AND ";
                }
                sql += "area like \"%" + city + "%\" ";
            }
        }
        return warehouseMapper.selectAllCountPage(sql);
    }

    public List<Warehouse> selectAllWarehouseBrief() {
        return warehouseMapper.selectAllWarehouseBrief();
    }

    public List<Warehouse> selectByArea(String area) {
        return warehouseMapper.selectByArea(area);
    }

    public List<Warehouse> selectByLevel(Integer level) {
        return warehouseMapper.selectByLevel(level);
    }

    public Warehouse selectById(Integer id) {
        return warehouseMapper.selectById(id);
    }

    public Warehouse selectByUserId(Integer id) {
        return warehouseMapper.selectByUserId(id);
    }

    public Warehouse selectByAreaAndLevel(String area, Integer level) {
        return warehouseMapper.selectByAreaAndLevel(area, level);
    }

    public Warehouse selectByName(String name) {
        return warehouseMapper.selectByName(name);
    }

    public String insert(Warehouse warehouse) {
        if (userMapper.selectById(warehouse.getUserId()) != null) {
            if (warehouseMapper.selectByUserId(warehouse.getUserId()) != null) {
                return "该员工已是管理员！";
            } else if (locationsService.selectByName(warehouse.getArea()) == null) {
                return "该地区不存在！";
            } else if (warehouseMapper.selectByName(warehouse.getName()) != null) {
                return "该仓库名字已经存在！";
            } else if (warehouseMapper.insert(warehouse)) {
                return "插入成功";
            } else return "插入失败";
        } else return "请输入正确的员工帐号";

    }

    public String update(Warehouse warehouse) {
        Float percent = warehouse.getResidueWeight() / warehouse.getMaxWeight();
        if (percent >= 1) {
            warehouse.setStatus(IS_FULL.getCode());
        }
        if (percent >= 0.8) {
            warehouse.setStatus(WILL_FULL.getCode());
        }
        if (userMapper.selectById(warehouse.getUserId()) != null) {
            if (warehouseMapper.selectByUserId(warehouse.getUserId()) != null && !warehouseMapper.selectByUserId(warehouse.getUserId()).getWarehouseId().equals(warehouse.getWarehouseId())) {
                return "该员工已是管理员！";
//            } else if (locationsService.selectByName(warehouse.getArea()) == null) {
//                return "该地区不存在！";
            } else if (warehouseMapper.selectByName(warehouse.getName()) != null && !warehouseMapper.selectByName(warehouse.getName()).getWarehouseId().equals(warehouse.getWarehouseId())) {
                return "该仓库名字已经存在！";
            } else if (warehouseMapper.update(warehouse)) {
                return "更新成功";
            } else return "更新失败";
        } else return "请输入正确的员工帐号";

    }

}