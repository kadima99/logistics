package com.lnsf.logistics.Enum;

public enum UserPriority {
    ADMIN(1, "super"), CENTERKEEPER(2, "centerWarehouseKeeper"), KEEPER(3, "warehouseKeeper"), DRIVER(4, "driver"), DELIVER(5, "deliver");
    private Integer code;
    private String Priority;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        this.Priority = priority;
    }

    @Override
    public String toString() {
        return "UserPriority{" +
                "code=" + code +
                ", Priority='" + Priority + '\'' +
                '}';
    }

    UserPriority() {

    }

    UserPriority(Integer code, String priority) {
        this.code = code;
        this.Priority = priority;
    }
}
