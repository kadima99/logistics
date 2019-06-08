package com.lnsf.logistics.entity;

public class User {

    private Integer userId;
    private String password;
    private String account;
    private String name;
    private String phone;
    private Integer priority;
    private Integer warehouseId;
    private Integer status;
    private Integer delMark;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelMark() {
        return delMark;
    }

    public void setDelMark(Integer delMark) {
        this.delMark = delMark;
    }

    public User() {

    }

    public User(Integer userId, String password, String name, String phone) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public User(String account,String password,  String name, String phone, Integer priority, Integer warehouseId, Integer status, Integer delMark) {
        this.password = password;
        this.account = account;
        this.name = name;
        this.phone = phone;
        this.priority = priority;
        this.warehouseId = warehouseId;
        this.status = status;
        this.delMark = delMark;
    }

    public User(Integer userId, String password, String account, String name, String phone, Integer priority, Integer warehouseId, Integer status, Integer delMark) {
        this.userId = userId;
        this.password = password;
        this.account = account;
        this.name = name;
        this.phone = phone;
        this.priority = priority;
        this.warehouseId = warehouseId;
        this.status = status;
        this.delMark = delMark;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", priority=" + priority +
                ", warehouseId=" + warehouseId +
                ", status=" + status +
                ", delMark=" + delMark +
                '}';
    }
}
