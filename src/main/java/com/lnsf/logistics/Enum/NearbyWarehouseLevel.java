package com.lnsf.logistics.Enum;

public enum NearbyWarehouseLevel {
    LEVEL_1(1,"地区之间"),LEVEL_2(2,"市级中心仓库之间"),LEVEL_3(3,"省级中心仓库之间");


    private Integer code;
    private String level;

    NearbyWarehouseLevel(Integer code, String level) {
        this.code = code;
        this.level = level;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
