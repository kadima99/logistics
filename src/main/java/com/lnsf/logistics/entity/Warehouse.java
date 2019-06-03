package com.lnsf.logistics.entity;

public class Warehouse {

    private Integer warehouseId;
    private String name;
    private String address;
    private Integer userId;
    private String area;
    private Integer level;
    private float maxWeight;
    private float residueWeight;
    private Integer status;

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }

    public float getResidueWeight() {
        return residueWeight;
    }

    public void setResidueWeight(float residueWeight) {
        this.residueWeight = residueWeight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Warehouse() {
    }

    public Warehouse(Integer warehouseId, String name, String address, Integer userId, String area , Integer level, float maxWeight, float residueWeight,Integer status) {
        this.warehouseId = warehouseId;
        this.name = name;
        this.address = address;
        this.userId = userId;
        this.level = level;
        this.area = area;
        this.maxWeight = maxWeight;
        this.residueWeight = residueWeight;
        this.status = status;
    }


    public Warehouse(String name, String address, Integer userId, String area , Integer level, float maxWeight, float residueWeight,Integer status) {
        this.name = name;
        this.address = address;
        this.userId = userId;
        this.level = level;
        this.area = area;
        this.maxWeight = maxWeight;
        this.residueWeight = residueWeight;
        this.status = status;
    }


    @Override
    public String toString() {
        return "Warehouse{" +
                "warehouseId=" + warehouseId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", userId=" + userId +
                ", area=" + area +'\''+
                ", level=" + level +
                ", maxWeight=" + maxWeight +
                ", residueWeight=" + residueWeight +
                ", status=" + status +

                '}';
    }

}
