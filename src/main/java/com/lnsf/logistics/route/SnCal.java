package com.lnsf.logistics.route;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;


//java版计算signature签名
public class SnCal {

    //起点-途径点-终点获取路径长度
    public String getDistance(Map<String, Object> map) {

        Map paramsMap = new LinkedHashMap<String, String>();

        paramsMap.put("origin", getLocation(map.get("origin").toString()));

        paramsMap.put("destination",getLocation(map.get("destination").toString()));

        paramsMap.put("ak", "68pI1e8zQYD6hUDG6GGWoiOHrvHofl6b");
        String result = HttpClientUtil.doGet("http://api.map.baidu.com/directionlite/v1/driving?",paramsMap);
        JsonElement je = new JsonParser().parse(result);
        JsonArray routes = je.getAsJsonObject().getAsJsonObject("result").getAsJsonArray("routes");
        String distance = routes.get(0).getAsJsonObject().get("distance").toString();
        return distance;
    }

    //起点-途径点-终点获取路径长度
    public String getDistanceWithWayPoints(Map<String, Object> map) {

        String way ="";
        Map paramsMap = new LinkedHashMap<String, String>();
        ArrayList<String> List = (ArrayList<String>) map.get("waypoints");

        paramsMap.put("origin", getLocation(map.get("origin").toString()));

        for (int i = 0; i < List.size(); i++) {
            way += getLocation(List.get(i));
            if(i!=List.size()-1){
                way += "|";
            }
        }
        paramsMap.put("waypoints",way);
        paramsMap.put("destination",getLocation(map.get("destination").toString()));
        paramsMap.put("ak", "68pI1e8zQYD6hUDG6GGWoiOHrvHofl6b");
        String result = HttpClientUtil.doGet("http://api.map.baidu.com/directionlite/v1/driving?",paramsMap);
        JsonElement je = new JsonParser().parse(result);
        JsonArray routes = je.getAsJsonObject().getAsJsonObject("result").getAsJsonArray("routes");
        String distance = routes.get(0).getAsJsonObject().get("distance").toString();
        return distance;
    }

    //起点-多终点获取路径
    public Map<String,Object> getDistanceWithDestination(Map<String, Object> map) {

        String destinations ="";
        Map paramsMap = new LinkedHashMap<String, String>();
        ArrayList<String> List = (ArrayList<String>) map.get("destinations");

        paramsMap.put("origins", getLocation(map.get("origins").toString()));

        for (int i = 0; i < List.size(); i++) {
            destinations += getLocation(List.get(i));
            if(i!=List.size()-1){
                destinations += "|";
            }
        }
        paramsMap.put("destinations",destinations);

        paramsMap.put("ak", "68pI1e8zQYD6hUDG6GGWoiOHrvHofl6b");
        String result = HttpClientUtil.doGet("http://api.map.baidu.com/routematrix/v2/driving?",paramsMap);
        JsonElement je = new JsonParser().parse(result);
        JsonArray distance = je.getAsJsonObject().getAsJsonArray("result");
        Map<String,Object> distanceMap = new HashMap<>();
        distanceMap.put("origin",getLocation(map.get("origins").toString()));
        for(int i=0;i<distance.size();i++){
            distanceMap.put(List.get(i),distance.get(i).getAsJsonObject().getAsJsonObject("distance").get("value").getAsInt());
        }
        return distanceMap;
    }

    //获取地点经纬度
    public String getLocation(String address) {

        java.text.DecimalFormat df = new java.text.DecimalFormat("#.######");
        Map paramsMap = new LinkedHashMap<String, String>();
        paramsMap.put("address", address);
        paramsMap.put("output", "json");
        paramsMap.put("ak", "68pI1e8zQYD6hUDG6GGWoiOHrvHofl6b");
        String result = HttpClientUtil.doGet("http://api.map.baidu.com/geocoder/v2/?", paramsMap);
        JsonElement je = new JsonParser().parse(result);
        Double lng = Double.valueOf(je.getAsJsonObject().getAsJsonObject("result").getAsJsonObject("location").get("lng").toString());
        Double lat = Double.valueOf(je.getAsJsonObject().getAsJsonObject("result").getAsJsonObject("location").get("lat").toString());
        return df.format(lat) + "," + df.format(lng);
    }

    // 对Map内所有value作utf8编码，拼接返回结果
    public String toQueryString(Map<?, ?> data)
            throws UnsupportedEncodingException {
        StringBuffer queryString = new StringBuffer();
        for (Entry<?, ?> pair : data.entrySet()) {
            queryString.append(pair.getKey() + "=");
            queryString.append(URLEncoder.encode((String) pair.getValue(),
                    "UTF-8") + "&");
        }
        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }
        return queryString.toString();
    }

    // 来自stackoverflow的MD5计算方法，调用了MessageDigest库函数，并把byte数组结果转换成16进制
    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static void main(String[] args)  {
        SnCal snCal = new SnCal();



//        snCal.getLocationUrl("广州市广园西路");
//        snCal.getLocationUrl("白云汇广场");


        //途径点测试
//        ArrayList<String> waypointsList = new ArrayList<>();
//        waypointsList.add("白云汇广场");
//        waypointsList.add("名汇广场");
        Map<String, Object> locationMap = new HashMap<>();
        locationMap.put("origin", "广东外语外贸大学(北校区)");
//       // locationMap.put("waypoints", waypointsList);
        locationMap.put("destination", "广州体育馆");
//
//
//
//        //一对多测试
//        ArrayList<String> destinationList = new ArrayList<>();
//        destinationList.add("广州白云汇广场");
//        destinationList.add("广州名汇广场");
//        Map<String, Object> locationMap_1 = new HashMap<>();
//        locationMap_1.put("origins", "广东外语外贸大学(北校区)");
//        locationMap_1.put("destinations", destinationList);
//        Map<String,Object> map = new HashMap<String,Object>();
//        map = snCal.getDistanceWithDestination(locationMap_1);
//        for (Object key : map.keySet()) {
//
//            System.out.println(key + " : " + map.get(key));
//        }
        System.out.println(snCal.getDistance(locationMap));




//        Gson gson = new Gson();
//        BaiDu lnsf = gson.fromJson(snCal.doGetTestOne(snCal.getLocationUrl("广东外语外贸大学(北校区)")), BaiDu.class);
//        BaiDu baidu = gson.fromJson(snCal.doGetTestOne(snCal.getLocationUrl("广州体育馆")),BaiDu.class);
//        String url = snCal.getDirectionUrl(lnsf.getResult().getLocation().getLng(),lnsf.getResult().getLocation().getLat(),
//                                baidu.getResult().getLocation().getLng(),baidu.getResult().getLocation().getLat());
//        System.out.println(url);

    }
}