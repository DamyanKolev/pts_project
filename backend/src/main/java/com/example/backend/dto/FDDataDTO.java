package com.example.backend.dto;

public class FDDataDTO {
    private String exercise;
    private double absoluteFrequency;
    private double relativeFrequency;

    public FDDataDTO(String exercise, double absoluteFrequency, double relativeFrequency) {
        setExercise(exercise);
        setAbsoluteFrequency(absoluteFrequency);
        setRelativeFrequency(relativeFrequency);
    }

    public String getExercise() {
        return this.exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public double getAbsoluteFrequency() {
        return this.absoluteFrequency;
    }

    public void setAbsoluteFrequency(double absoluteFrequency) {
        this.absoluteFrequency = absoluteFrequency;
    }

    public double getRelativeFrequency() {
        return this.relativeFrequency;
    }

    public void setRelativeFrequency(double relativeFrequency) {
        this.relativeFrequency = relativeFrequency;
    }

}
