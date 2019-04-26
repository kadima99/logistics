package com.lnsf.logistics.entity;

public class User {

    private Integer id;
    private Integer userId;
    private String password;
    private String name;
    private String phone;
    private Integer priority;
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public User(Integer id, Integer userId, String password, String name, String phone, Integer priority, Integer state) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.priority = priority;
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId=" + userId +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", priority=" + priority +
                ", state=" + state +
                '}';
    }
}
