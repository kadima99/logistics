package com.lnsf.logistics.entity;

public class Locations {

    private Integer id;
    private String name;
    private Integer parentId;
    private Integer level;


    public Locations(Integer id, String name, Integer parentId, Integer level) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.level = level;
    }

    public Locations(String name, Integer parentId, Integer level) {
        this.name = name;
        this.parentId = parentId;
        this.level = level;
    }

    public Locations() {
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Locations{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", level=" + level +
                '}';
    }
}
