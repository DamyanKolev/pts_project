package com.example.backend.dto;

import java.util.List;

public class DMDataDTO {
    private double deviation;
    private double dispersion;
    private double scope;
    private List<TableData> tableData;

    public DMDataDTO(double deviation, double dispersion, double scope, List<TableData> tableData) {
        setDeviation(deviation);
        setDispersion(dispersion);
        setScope(scope);
        setTableData(tableData);
    }

    public double getDeviation() {
        return this.deviation;
    }

    public void setDeviation(double deviation) {
        this.deviation = deviation;
    }

    public double getDispersion() {
        return this.dispersion;
    }

    public void setDispersion(double dispersion) {
        this.dispersion = dispersion;
    }

    public double getScope() {
        return this.scope;
    }

    public void setScope(double scope) {
        this.scope = scope;
    }

    public List<TableData> getTableData() {
        return this.tableData;
    }

    public void setTableData(List<TableData> tableData) {
        this.tableData = tableData;
    }

}
