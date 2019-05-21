package com.lnsf.logistics.entity;

import java.sql.Date;
import java.sql.Time;

public class CarInfo {
    private Integer carId;
    private Integer handoverOrderId;
    private Integer nextWarehouseId;
    private Date waitTime;
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

    public Integer getNextWarehouseId() {
        return nextWarehouseId;
    }

    public void setNextWarehouseId(Integer nextWarehouseId) {
        this.nextWarehouseId = nextWarehouseId;
    }

    public Date getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Date waitTime) {
        this.waitTime = waitTime;
    }

    public Integer getDelMark() {
        return delMark;
    }

    public void setDelMark(Integer delMark) {
        this.delMark = delMark;
    }

    public CarInfo() {
    }

    public CarInfo(Integer carId, Integer handoverOrderId, Integer nextWarehouseId, Date waitTime, Integer delMark) {
        this.carId = carId;
        this.handoverOrderId = handoverOrderId;
        this.nextWarehouseId = nextWarehouseId;
        this.waitTime = waitTime;
        this.delMark = delMark;
    }

    @Override
    public String toString() {
        return "CarInfo{" +
                "carId=" + carId +
                ", handoverOrderId=" + handoverOrderId +
                ",nextWarehouseId=" + nextWarehouseId +
                ",waitTime=" + waitTime +
                ", delMark=" + delMark +
                '}';
    }
}
