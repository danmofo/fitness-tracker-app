package com.dmoffat.fitnesstracker.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class UserBodyWeight {
    private Integer id;
    private Integer userId;
    private Double weight;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate loggedOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public LocalDate getLoggedOn() {
        return loggedOn;
    }

    public void setLoggedOn(LocalDate loggedOn) {
        this.loggedOn = loggedOn;
    }
}
