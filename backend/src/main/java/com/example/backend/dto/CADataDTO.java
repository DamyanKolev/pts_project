package com.example.backend.dto;

import java.util.List;

public class CADataDTO {
    private String dependence;
    private List<GraphicData> dataset;

    public CADataDTO(String dependence, List<GraphicData> dataset) {
        setDependence(dependence);
        setDataset(dataset);
    }

    public String getDependence() {
        return this.dependence;
    }

    public void setDependence(String dependence) {
        this.dependence = dependence;
    }

    public List<GraphicData> getDataset() {
        return this.dataset;
    }

    public void setDataset(List<GraphicData> dataset) {
        this.dataset = dataset;
    }

}
