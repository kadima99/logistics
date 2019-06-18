package com.lnsf.logistics.entity;


import java.sql.Time;
import java.sql.Timestamp;

public class InboundOrder {
    private Integer inboundOrderId;
    private Integer orderId;
    private Integer warehouseId;
    private Integer delMark;
    private Timestamp createDate;

    public Integer getInboundOrderId() {
        return inboundOrderId;
    }

    public void setInboundOrderId(Integer inboundOrderId) {
        this.inboundOrderId = inboundOrderId;
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

    public Integer getDelMark() {
        return delMark;
    }

    public void setDelMark(Integer delMark) {
        this.delMark = delMark;
    }

    public Timestamp  getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public InboundOrder() {
    }

    public InboundOrder(Integer inboundOrderId, Integer orderId, Integer warehouseId, Integer delMark) {
        this.inboundOrderId = inboundOrderId;
        this.orderId = orderId;
        this.warehouseId = warehouseId;
        this.delMark = delMark;
    }

    public InboundOrder(Integer orderId, Integer warehouseId, Integer delMark) {
        this.orderId = orderId;
        this.warehouseId = warehouseId;
        this.delMark = delMark;
    }

    @Override
    public String toString() {
        return "InboundOrder{" +
                "inboundOrderId=" + inboundOrderId +
                ", orderId=" + orderId +
                ", warehouseId=" + warehouseId +
                ", delMark=" + delMark +
                ", createDate=" + createDate +
                '}';
    }
}
