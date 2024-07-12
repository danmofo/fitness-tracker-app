package com.dmoffat.fitnesstracker.model;

public class Workout {
    private Integer id;
    private User user;

    public Workout() {
    }

    public Workout(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
