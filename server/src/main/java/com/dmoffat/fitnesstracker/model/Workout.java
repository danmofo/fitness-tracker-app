package com.dmoffat.fitnesstracker.model;

import java.time.LocalDateTime;

public class Workout {
    private Integer id;
    private User user;
    private String notes;
    private LocalDateTime startedOn;
    private LocalDateTime finishedOn;

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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
    }

    public LocalDateTime getFinishedOn() {
        return finishedOn;
    }

    public void setFinishedOn(LocalDateTime finishedOn) {
        this.finishedOn = finishedOn;
    }
}
