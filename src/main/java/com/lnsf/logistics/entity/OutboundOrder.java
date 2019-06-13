package com.lnsf.logistics.entity;

import java.util.Date;

public class OutboundOrder {
    private Integer outboundOrderId;
    private Integer warehouseId;
    private Integer nextWarehouseId;
    private Integer orderId;
    private Integer delMark;
    private Date createDate;

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

    public Integer getDelMark() {
        return delMark;
    }

    public void setDelMark(Integer delMark) {
        this.delMark = delMark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public OutboundOrder() {
    }

    public OutboundOrder(Integer outboundOrderId, Integer warehouseId, Integer nextWarehouseId, Integer orderId, Integer delMark, Date createDate) {
        this.outboundOrderId = outboundOrderId;
        this.warehouseId = warehouseId;
        this.nextWarehouseId = nextWarehouseId;
        this.orderId = orderId;
        this.delMark = delMark;
        this.createDate = createDate;
    }

    public OutboundOrder(Integer warehouseId, Integer nextWarehouseId, Integer orderId, Integer delMark) {
        this.outboundOrderId = outboundOrderId;
        this.warehouseId = warehouseId;
        this.nextWarehouseId = nextWarehouseId;
        this.orderId = orderId;
        this.delMark = delMark;
        this.createDate = createDate;
    }


    @Override
    public String toString() {
        return "OutboundOrder{" +
                "outboundOrderId=" + outboundOrderId +
                ", warehouseId=" + warehouseId +
                ", nextWarehouseId=" + nextWarehouseId +
                ", orderId=" + orderId +
                ", delMark=" + delMark +
                ", createDate=" + createDate +
                '}';
    }
}
