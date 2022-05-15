package com.example.backend.dto;

public class GraphicData {
    private int assessment;
    private int wikiUpdates;

    public GraphicData(int assessment, int wikiUpdates) {
        setAssessment(assessment);
        setWikiUpdates(wikiUpdates);
    }

    public int getAssessment() {
        return this.assessment;
    }

    public void setAssessment(int assessment) {
        this.assessment = assessment;
    }

    public int getWikiUpdates() {
        return this.wikiUpdates;
    }

    public void setWikiUpdates(int wikiUpdates) {
        this.wikiUpdates = wikiUpdates;
    }

}
