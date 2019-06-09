package com.lnsf.logistics.Enum;

public enum CarLevel {
    LEVEL_1(1,"地区之内的车辆"),LEVEL_2(2,"市级中心仓库之间的车辆"),LEVEL_3(3,"省级中心仓库之间的车辆");


    private Integer code;
    private String level;

    CarLevel(Integer code, String level) {
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
