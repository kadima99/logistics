package com.lnsf.logistics.entity;

public class Customer {

    private Integer customerId;
    private String account;
    private String password;
    private String name;
    private String phone;
    private Integer status;
    private Integer delMark;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDelMark() {
        return delMark;
    }

    public void setDelMark(Integer delMark) {
        this.delMark = delMark;
    }

    public Customer() {
    }

    public Customer(Integer customerId, String account, String password, String name, String phone, Integer status, Integer delMark) {
        this.customerId = customerId;
        this.account = account;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.delMark = delMark;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", delMark=" + delMark +
                '}';
    }
}
