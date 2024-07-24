package com.dmoffat.fitnesstracker.model;

import java.util.List;

public class ExerciseWithCompletedSets {
    private Integer id;
    private String name;
    private List<CompletedSet> completed;

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

    public List<CompletedSet> getCompleted() {
        return completed;
    }

    public void setCompleted(List<CompletedSet> completed) {
        this.completed = completed;
    }
}
