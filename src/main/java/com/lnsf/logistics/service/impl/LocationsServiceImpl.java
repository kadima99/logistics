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
    public Locations selectByNameAndParentId(String name,Integer id){
        return locationsMapper.selectByNameAndParentId(name,id);
    }
    @Override
    public Locations selectById(Integer id) {
        return locationsMapper.selectById(id);
    }

    @Override
    public List<Locations> selectByParentId(Integer id) {
        return locationsMapper.selectByParentId(id);
    }

    @Override
    public Map<String, Object> selectLocationsByAddress(String address) {
        Map<String, Object> map = new HashMap<String, Object>();
        String regex = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m = Pattern.compile(regex).matcher(address);
        String province = "", city = "", county = "";
        while (m.find()) {
            province = m.group("province");
            map.put("province", locationsMapper.selectByName(province));
            city = m.group("city");
            if (city.equals("市辖区")) {
                map.put("city", locationsMapper.selectByNameAndParentId(city, locationsMapper.selectByName(province).getId()));
            } else map.put("city", locationsMapper.selectByName(city));
            county = m.group("county");
            if (locationsMapper.selectByName(county) != null) {
                map.put("county", locationsMapper.selectByName(county));
                map.put("flag", 3);
            } else map.put("flag", 2);
        }
        return map;
    }

}