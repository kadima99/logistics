package com.lnsf.logistics.entity;

public class Warehouse {

    private Integer warehouseId;
    private String name;
    private String address;
    private Integer userId;
    private Integer delMark;

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDelMark() {
        return delMark;
    }

    public void setDelMark(Integer delMark) {
        this.delMark = delMark;
    }

    public Warehouse() {
    }

    public Warehouse(Integer warehouseId, String name, String address, Integer userId, Integer delMark) {
        this.warehouseId = warehouseId;
        this.name = name;
        this.address = address;
        this.userId = userId;
        this.delMark = delMark;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "warehouseId=" + warehouseId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", userId=" + userId +
                ", delMark=" + delMark +
                '}';
    }
}
