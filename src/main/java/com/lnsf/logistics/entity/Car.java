package com.lnsf.logistics.entity;

public class Car {
    private Integer carId;
    private Integer userId;
    private Float maxWeight;
    private Float residueWeight;
    private Integer status;


    private Integer level;
    private Integer lineId;
    private Integer delMark;

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

    public Integer getDelMark() {
        return delMark;
    }

    public void setDelMark(Integer delMark) {
        this.delMark = delMark;
    }

    public Car() {
    }

    public Car(Integer carId, Integer userId, Float maxWeight, Float residueWeight, Integer status,Integer level,Integer lineId, Integer delMark) {
        this.carId = carId;
        this.userId = userId;
        this.maxWeight = maxWeight;
        this.residueWeight = residueWeight;
        this.status = status;
        this.level = level;
        this.lineId = lineId;
        this.delMark = delMark;
    }

    public Car(Integer userId, Float maxWeight, Float residueWeight, Integer status, Integer level,Integer lineId, Integer delMark) {
        this.userId = userId;
        this.maxWeight = maxWeight;
        this.residueWeight = residueWeight;
        this.status = status;
        this.level = level;
        this.lineId = lineId;
        this.delMark = delMark;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", userId=" + userId +
                ", maxWeight=" + maxWeight +
                ", residueWeight=" + residueWeight +
                ",status=" + status +
                ",level=" + level +
                ",lineId=" + lineId +
                ", delMark=" + delMark +
                '}';
    }
}
