package com.lnsf.logistics.entity.logistics.entity;

public class OutboundOrder {
    private Integer outboundOrderId;
    private Integer warehouseId;
    private Integer nextWarehouseId;
    private Integer orderId;
    private Integer carId;
    private Integer delMark;

    public Integer getOutboundOrderId() {
        return outboundOrderId;
    }

    public void setOutboundOrderId(Integer outboundOrderId) {
        this.outboundOrderId = outboundOrderId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getNextWarehouseId() {
        return nextWarehouseId;
    }

    public void setNextWarehouseId(Integer nextWarehouseId) {
        this.nextWarehouseId = nextWarehouseId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getDelMark() {
        return delMark;
    }

    public void setDelMark(Integer delMark) {
        this.delMark = delMark;
    }

    public OutboundOrder() {
    }

    public OutboundOrder(Integer outboundOrderId, Integer warehouseId, Integer nextWarehouseId, Integer orderId, Integer carId, Integer delMark) {
        this.outboundOrderId = outboundOrderId;
        this.warehouseId = warehouseId;
        this.nextWarehouseId = nextWarehouseId;
        this.orderId = orderId;
        this.carId = carId;
        this.delMark = delMark;
    }

    @Override
    public String toString() {
        return "OutboundOrder{" +
                "outboundOrderId=" + outboundOrderId +
                ", warehouseId=" + warehouseId +
                ", nextWarehouseId=" + nextWarehouseId +
                ", orderId=" + orderId +
                ", carId=" + carId +
                ", delMark=" + delMark +
                '}';
    }
}
