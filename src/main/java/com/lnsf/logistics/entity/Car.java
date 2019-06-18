package com.lnsf.logistics.entity;

public class Car {
    private Integer carId;
    private Integer userId;
    private Float maxWeight;
    private Float residueWeight;
    private Integer status;
    private Integer level;
    private Integer lineId;
    private Integer warehouseId;
    private Integer nowWarehouseId;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Float getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Float maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Float getResidueWeight() {
        return residueWeight;
    }

    public void setResidueWeight(Float residueWeight) {
        this.residueWeight = residueWeight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getNowWarehouseId() {
        return nowWarehouseId;
    }

    public void setNowWarehouseId(Integer nowWarehouseId) {
        this.nowWarehouseId = nowWarehouseId;
    }

    public Car() {
    }

    public Car(Integer carId, Integer userId, Float maxWeight, Float residueWeight, Integer status, Integer level, Integer lineId, Integer warehouseId, Integer nowWarehouseId) {
        this.carId = carId;
        this.userId = userId;
        this.maxWeight = maxWeight;
        this.residueWeight = residueWeight;
        this.status = status;
        this.level = level;
        this.lineId = lineId;
        this.warehouseId = warehouseId;
        this.nowWarehouseId = nowWarehouseId;

    }

    public Car(Float maxWeight, Float residueWeight, Integer status, Integer level, Integer warehouseId,Integer nowWarehouseId) {
        this.maxWeight = maxWeight;
        this.residueWeight = residueWeight;
        this.status = status;
        this.level = level;
        this.warehouseId = warehouseId;
        this.nowWarehouseId = nowWarehouseId;

    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", userId=" + userId +
                ", maxWeight=" + maxWeight +
                ", residueWeight=" + residueWeight +
                ", status=" + status +
                ", level=" + level +
                ", lineId=" + lineId +
                ", warehouseId=" + warehouseId +
                ", nowWarehouseId=" + nowWarehouseId +
                '}';
    }
}
