package com.example.backend.dto;

import java.util.List;

public class MCTDataDTO {
    private double averageValue;
    private double median;
    private int mode;
    private List<TableData> tableData;

    public MCTDataDTO(double averageValue, double median, int mode, List<TableData> tableData) {
        setAverageValue(averageValue);
        setMedian(median);
        setMode(mode);
        setTableData(tableData);
    }

    public double getAverageValue() {
        return this.averageValue;
    }

    public void setAverageValue(double averageValue) {
        this.averageValue = averageValue;
    }

    public double getMedian() {
        return this.median;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public List<TableData> getTableData() {
        return this.tableData;
    }

    public void setTableData(List<TableData> tableData) {
        this.tableData = tableData;
    }

}
