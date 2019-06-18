package com.lnsf.logistics.entity;


import java.sql.Time;
import java.sql.Timestamp;
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
    private Timestamp createDate;
    private Timestamp confirmDate;
    private Integer status;
    private Integer warehouseId;
    private Integer endWarehouseId;
    private Integer userId;

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

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Timestamp confirmDate) {
        this.confirmDate = confirmDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getEndWarehouseId() {
        return endWarehouseId;
    }

    public void setEndWarehouseId(Integer endWarehouseId) {
        this.endWarehouseId = endWarehouseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Orders() {
    }

    public Orders(Integer customerId, String customerName, String customerPhone, String customerAddress, String receiverName, String receiverPhone, String receiverAddress, Integer status, Integer warehouseId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverAddress = receiverAddress;
        this.status = status;
        this.warehouseId = warehouseId;
    }

    public Orders(Integer customerId, String customerName, String customerPhone, String customerProvince, String customerCity, String customerAddress, String receiverName, String receiverPhone, String receiverProvince, String receiverCity, String receiverAddress, Float wareWeight, Float freight, Integer status, Integer warehouseId, Integer endWarehouseId) {

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
        this.status = status;
        this.warehouseId = warehouseId;
        this.endWarehouseId = endWarehouseId;
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
                ", status=" + status +
                ", warehouseId=" + warehouseId +
                ", endWarehouseId=" + endWarehouseId +
                ", userId=" + userId +
                '}';
    }
}
