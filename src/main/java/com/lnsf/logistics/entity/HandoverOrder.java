package com.lnsf.logistics.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class HandoverOrder {
    private Integer handoverOrderId;
    private Integer userId;
    private Integer outboundId;
    private Integer delMark;
    private Timestamp createDate;
    private Integer warehouseId;
    private Integer flag;


    public Integer getHandoverOrderId() {
        return handoverOrderId;
    }

    public void setHandoverOrderId(Integer handoverOrderId) {
        this.handoverOrderId = handoverOrderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOutboundId() {
        return outboundId;
    }

    public void setOutboundId(Integer outboundId) {
        this.outboundId = outboundId;
    }

    public Integer getDelMark() {
        return delMark;
    }

    public void setDelMark(Integer delMark) {
        this.delMark = delMark;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public HandoverOrder() {
    }

    public HandoverOrder(Integer userId, Integer outboundId, Integer warehouseId,Integer flag,Integer delMark) {

        this.userId = userId;
        this.outboundId = outboundId;
        this.warehouseId = warehouseId;
        this.flag = flag;
        this.delMark = delMark;

    }

    public HandoverOrder(Integer handoverOrderId,Integer userId, Integer outboundId,Integer warehouseId, Integer flag,Integer delMark) {
        this.handoverOrderId = handoverOrderId;
        this.userId = userId;
        this.outboundId = outboundId;
        this.warehouseId = warehouseId;
        this.flag = flag;
        this.delMark = delMark;

    }

    @Override
    public String toString() {
        return "HandoverOrder{" +
                "handoverOrderId=" + handoverOrderId +
                ", userId=" + userId +
                ", outboundId=" + outboundId +
                ", delMark=" + delMark +
                ", createDate=" + createDate +
                ", warehouseId=" + warehouseId +
                ", flag=" + flag +
                '}';
    }
}
