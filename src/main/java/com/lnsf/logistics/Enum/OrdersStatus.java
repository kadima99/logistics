package com.lnsf.logistics.Enum;

public enum OrdersStatus {
    WAIT_FOR(0,"等待配送"),IS_DELIVERING(1,"配送中"),WAS_FINISHED(2,"订单完成"),WAIT_FRO_TO_THEEND(3,"等待配送最终的仓库"),WAIT_FOR_TO_WAREHOUSE(4,"等待入库");


    private Integer code;
    private String status;

    OrdersStatus(Integer code, String status) {
        this.code = code;
        this.status = status;
    }

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

}
