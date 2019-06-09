package com.lnsf.logistics.service.impl;

import com.lnsf.logistics.entity.Locations;
import com.lnsf.logistics.mapper.LocationsMapper;
import com.lnsf.logistics.service.LocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class LocationsServiceImpl implements LocationsService {

    @Autowired
    private LocationsMapper locationsMapper;

    @Override
    public List<Locations> selectAll() {
        return locationsMapper.selectAll();
    }

    @Override
    public Locations selectByName(String name) {
        return locationsMapper.selectByName(name);
    }

    @Override
    public Locations selectById(Integer id) {
        return locationsMapper.selectById(id);
    }

    @Override
    public Map<String,Object> selectLocationsByAddress(String address){
        Map<String,Object> map = new HashMap<String, Object>();
        String regex = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m = Pattern.compile(regex).matcher(address);
        String province = "", city = "", county = "";
        while (m.find()) {
            province = m.group("province");
            map.put("province",province);
            city = m.group("city");
            map.put("city",city);
            county = m.group("county");
            map.put("county",county);
        }

        return map;
    }

}