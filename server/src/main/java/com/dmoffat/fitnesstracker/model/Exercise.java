package com.dmoffat.fitnesstracker.model;

public class Exercise {
    private Integer id;
    private String name;
    private String brand;
    private Type type;

    public enum Type {
        FREE_WEIGHT,
        MACHINE
    }

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
