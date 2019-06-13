package com.lnsf.logistics.entity;

import java.util.Date;

public class HandoverOrder {
    private Integer handoverOrderId;
    private Integer userId;
    private Integer outboundId;
    private Integer delMark;
    private Date createDate;


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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public HandoverOrder() {
    }

    public HandoverOrder(Integer userId, Integer outboundId, Integer delMark) {

        this.userId = userId;
        this.outboundId = outboundId;
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
                '}';
    }
}
