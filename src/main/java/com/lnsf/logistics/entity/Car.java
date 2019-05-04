package com.lnsf.logistics.entity;

public class Car {
    private Integer carId;
    private Integer userId;
    private Float maxWeight;
    private Float residueWeight;
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

    public Integer getDelMark() {
        return delMark;
    }

    public void setDelMark(Integer delMark) {
        this.delMark = delMark;
    }

    public Car() {
    }

    public Car(Integer carId, Integer userId, Float maxWeight, Float residueWeight, Integer delMark) {
        this.carId = carId;
        this.userId = userId;
        this.maxWeight = maxWeight;
        this.residueWeight = residueWeight;
        this.delMark = delMark;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", userId=" + userId +
                ", maxWeight=" + maxWeight +
                ", residueWeight=" + residueWeight +
                ", delMark=" + delMark +
                '}';
    }
}
