package com.lnsf.logistics.entity;

import java.sql.Time;

public class NearbyWarehouse {
    private Integer id;
    private Integer nearbyWarehouseId;
    private Integer warehouseId;
    private Integer level;
    private Integer delMark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNearbyWarehouseId() {
        return nearbyWarehouseId;
    }

    public void setNearbyWarehouseId(Integer nearbyWarehouseId) {
        this.nearbyWarehouseId = nearbyWarehouseId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getDelMark() {
        return delMark;
    }

    public void setDelMark(Integer delMark) {
        this.delMark = delMark;
    }

    public NearbyWarehouse() {
    }

    public NearbyWarehouse(Integer id, Integer nearbyWarehouseId, Integer warehouseId, Integer level, Integer delMark) {
        this.id = id;
        this.nearbyWarehouseId = nearbyWarehouseId;
        this.warehouseId = warehouseId;
        this.level = level;
        this.delMark = delMark;
    }

    public NearbyWarehouse(Integer nearbyWarehouseId, Integer warehouseId, Integer level, Integer delMark) {

        this.nearbyWarehouseId = nearbyWarehouseId;
        this.warehouseId = warehouseId;
        this.level = level;
        this.delMark = delMark;
    }

    @Override
    public String toString() {
        return "NearbyWarehouse{" +
                "id=" + id +
                ", nearbyWarehouseId=" + nearbyWarehouseId +
                ", warehouseId=" + warehouseId +
                ", level=" + level +
                ", delMark=" + delMark +
                '}';
    }
}
