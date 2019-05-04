package com.lnsf.logistics.entity;

public class NearbyWarehouse {
    private Integer id;
    private Integer nearbyWarehouseId;
    private Integer warehouseId;
    private Float distance;
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

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Integer getDelMark() {
        return delMark;
    }

    public void setDelMark(Integer delMark) {
        this.delMark = delMark;
    }

    public NearbyWarehouse() {
    }

    public NearbyWarehouse(Integer id, Integer nearbyWarehouseId, Integer warehouseId, Float distance, Integer delMark) {
        this.id = id;
        this.nearbyWarehouseId = nearbyWarehouseId;
        this.warehouseId = warehouseId;
        this.distance = distance;
        this.delMark = delMark;
    }

    @Override
    public String toString() {
        return "NearbyWarehouse{" +
                "id=" + id +
                ", nearbyWarehouseId=" + nearbyWarehouseId +
                ", warehouseId=" + warehouseId +
                ", distance=" + distance +
                ", delMark=" + delMark +
                '}';
    }
}
