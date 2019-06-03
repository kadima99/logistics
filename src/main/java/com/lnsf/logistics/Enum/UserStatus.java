package com.lnsf.logistics.Enum;

public enum UserStatus {
    IS_BUSY("配送中",1),NO_BUSY ("空闲中",2);


    private String status;
    private int code;

    private UserStatus(String status,int code){
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
