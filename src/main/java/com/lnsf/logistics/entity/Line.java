package com.lnsf.logistics.entity;

public class Line {
    private Integer lineId;
    private String lineSummary;
    private Integer beginId;
    private Integer endId;
    private Integer delMark;

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public String getLineSummary() {
        return lineSummary;
    }

    public void setLineSummary(String lineSummary) {
        this.lineSummary = lineSummary;
    }

    public Integer getBeginId() {
        return beginId;
    }

    public void setBeginId(Integer beginId) {
        this.beginId = beginId;
    }

    public Integer getEndId() {
        return endId;
    }

    public void setEndId(Integer endId) {
        this.endId = endId;
    }

    public Integer getDelMark() {
        return delMark;
    }

    public void setDelMark(Integer delMark) {
        this.delMark = delMark;
    }

    public Line() {
    }

    public Line(Integer lineId, String lineSummary, Integer beginId, Integer endId, Integer delMark) {
        this.lineId = lineId;
        this.lineSummary = lineSummary;
        this.beginId = beginId;
        this.endId = endId;
        this.delMark = delMark;
    }

    public Line(String lineSummary, Integer beginId, Integer endId, Integer delMark) {
        this.lineSummary = lineSummary;
        this.beginId = beginId;
        this.endId = endId;
        this.delMark = delMark;
    }

    @Override
    public String toString() {
        return "Line{" +
                "lineId=" + lineId +
                ", lineSummary='" + lineSummary + '\'' +
                ", beginId=" + beginId +
                ", endId=" + endId +
                ", delMark=" + delMark +
                '}';
    }
}
