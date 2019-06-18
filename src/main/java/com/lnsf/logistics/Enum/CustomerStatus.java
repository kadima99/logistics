package com.lnsf.logistics.Enum;

public enum CustomerStatus {
    IS_USING("使用中",1),FORBID("封禁中",0);


    private String status;
    private int code;

    private CustomerStatus(String status,int code){
        this.status = status;
        this.code =code;
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
