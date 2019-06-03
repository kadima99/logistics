package com.lnsf.logistics.entity;

public class Area {

    private Integer id;
    private String name;

    public Area(){

    }

    public Area(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AreaMapper{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
