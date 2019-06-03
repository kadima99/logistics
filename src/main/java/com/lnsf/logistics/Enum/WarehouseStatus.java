package com.lnsf.logistics.Enum;

public enum WarehouseStatus {
    NO_FULL("未满",0),WILL_FULL("超过90%", 1), IS_FULL("已满", 2);


    private String status;
    private int code;

    private WarehouseStatus(String status, int code) {
        this.status = status;
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
