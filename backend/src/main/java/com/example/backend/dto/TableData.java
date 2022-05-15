package com.example.backend.dto;

public class TableData {
    private int id;
    private int exercises;

    public TableData(int id, int exercises) {
        setId(id);
        setExercise(exercises);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExercises() {
        return this.exercises;
    }

    public void setExercise(int exercises) {
        this.exercises = exercises;
    }

}
