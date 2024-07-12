package com.dmoffat.fitnesstracker.model;

public class Exercise {
    private Integer id;
    private String name;

    public Exercise() {

    }

    public Exercise(Integer id) {
        this.id = id;
    }

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
}
