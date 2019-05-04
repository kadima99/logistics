package com.lnsf.logistics.entity;

public class Delivery {

    private Integer deliveryId;
    private Integer orderId;
    private Integer warehouseId;
    private Integer userId;
    private Integer delMark;

    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
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

    public Delivery() {
    }

    public Delivery(Integer deliveryId, Integer orderId, Integer warehouseId, Integer userId, Integer delMark) {
        this.deliveryId = deliveryId;
        this.orderId = orderId;
        this.warehouseId = warehouseId;
        this.userId = userId;
        this.delMark = delMark;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryId=" + deliveryId +
                ", orderId=" + orderId +
                ", warehouseId=" + warehouseId +
                ", userId=" + userId +
                ", delMark=" + delMark +
                '}';
    }
}
