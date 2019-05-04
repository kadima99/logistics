package com.lnsf.logistics.entity;


import java.util.Date;

public class Orders {
    private Integer orderId;
    private Integer customerId;
    private String customerName;
    private String customerPhone;
    private String customerProvince;
    private String customerCity;
    private String customerAddress;
    private String receiverName;
    private String receiverPhone;
    private String receiverProvince;
    private String receiverCity;
    private String receiverAddress;
    private Float wareWeight;
    private Float freight;
    private Date createDate;
    private Date confirmDate;
    private Integer lineId;
    private Integer goodsState;
    private Integer delMark;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerProvince() {
        return customerProvince;
    }

    public void setCustomerProvince(String customerProvince) {
        this.customerProvince = customerProvince;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public Float getWareWeight() {
        return wareWeight;
    }

    public void setWareWeight(Float wareWeight) {
        this.wareWeight = wareWeight;
    }

    public Float getFreight() {
        return freight;
    }

    public void setFreight(Float freight) {
        this.freight = freight;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getGoodsState() {
        return goodsState;
    }

    public void setGoodsState(Integer goodsState) {
        this.goodsState = goodsState;
    }

    public Integer getDelMark() {
        return delMark;
    }

    public void setDelMark(Integer delMark) {
        this.delMark = delMark;
    }

    public Orders() {
    }

    public Orders(Integer orderId, Integer customerId, String customerName, String customerPhone, String customerProvince, String customerCity, String customerAddress, String receiverName, String receiverPhone, String receiverProvince, String receiverCity, String receiverAddress, Float wareWeight, Float freight, Date createDate, Date confirmDate, Integer lineId, Integer goodsState, Integer delMark) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerProvince = customerProvince;
        this.customerCity = customerCity;
        this.customerAddress = customerAddress;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverProvince = receiverProvince;
        this.receiverCity = receiverCity;
        this.receiverAddress = receiverAddress;
        this.wareWeight = wareWeight;
        this.freight = freight;
        this.createDate = createDate;
        this.confirmDate = confirmDate;
        this.lineId = lineId;
        this.goodsState = goodsState;
        this.delMark = delMark;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerProvince='" + customerProvince + '\'' +
                ", customerCity='" + customerCity + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverProvince='" + receiverProvince + '\'' +
                ", receiverCity='" + receiverCity + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", wareWeight=" + wareWeight +
                ", freight=" + freight +
                ", createDate=" + createDate +
                ", confirmDate=" + confirmDate +
                ", lineId=" + lineId +
                ", goodsState=" + goodsState +
                ", delMark=" + delMark +
                '}';
    }
}
