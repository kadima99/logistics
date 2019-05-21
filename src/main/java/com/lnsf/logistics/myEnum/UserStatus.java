package com.lnsf.logistics.myEnum;

public enum UserStatus {
    WAS_BUSY("配送中",1),;


    private String status;
    private int code;

    private UserStatus(){

    }
    private UserStatus(String status,int code){

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
