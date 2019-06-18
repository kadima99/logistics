package com.lnsf.logistics.Enum;

public enum CarStatus {
    IS_BUSY(1,"使用中"),NO_BUSY(2,"空闲中"),MAINTENANCE(3,"维修中"),FULL(4,"已满"),GIFT(5,"废弃");


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    CarStatus(Integer code, String status) {
        this.code = code;
        this.status = status;
    }

    private Integer code;
    private String status;
}
