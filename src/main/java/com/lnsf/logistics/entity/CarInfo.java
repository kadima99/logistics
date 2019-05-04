package com.lnsf.logistics.entity;

public class CarInfo {
    private Integer carId;
    private Integer handoverOrderId;
    private Integer delMark;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getHandoverOrderId() {
        return handoverOrderId;
    }

    public void setHandoverOrderId(Integer handoverOrderId) {
        this.handoverOrderId = handoverOrderId;
    }

    public Integer getDelMark() {
        return delMark;
    }

    public void setDelMark(Integer delMark) {
        this.delMark = delMark;
    }

    public CarInfo() {
    }

    public CarInfo(Integer carId, Integer handoverOrderId, Integer delMark) {
        this.carId = carId;
        this.handoverOrderId = handoverOrderId;
        this.delMark = delMark;
    }

    @Override
    public String toString() {
        return "CarInfo{" +
                "carId=" + carId +
                ", handoverOrderId=" + handoverOrderId +
                ", delMark=" + delMark +
                '}';
    }
}
