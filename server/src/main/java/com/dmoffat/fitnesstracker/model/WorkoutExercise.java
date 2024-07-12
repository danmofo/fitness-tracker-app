package com.dmoffat.fitnesstracker.model;

import java.time.LocalDateTime;
import java.util.List;

public class WorkoutExercise {
    private Integer id;
    private Workout workout;
    private Exercise exercise;
    private Integer weight;
    private Integer sets;
    private Integer reps;
    private String notes;
    private List<String> equipment;
    private LocalDateTime createdOn;

    public WorkoutExercise() {
    }

    public WorkoutExercise(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<String> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<String> equipment) {
        this.equipment = equipment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkoutExercise that = (WorkoutExercise) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "WorkoutExercise{" +
            "id=" + id +
            ", workout=" + workout +
            ", exercise=" + exercise +
            ", weight=" + weight +
            ", sets=" + sets +
            ", reps=" + reps +
            ", notes='" + notes + '\'' +
            ", equipment=" + equipment +
            ", createdOn=" + createdOn +
            '}';
    }
}
