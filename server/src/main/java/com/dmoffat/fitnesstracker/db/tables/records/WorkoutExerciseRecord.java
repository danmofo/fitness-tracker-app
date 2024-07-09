/*
 * This file is generated by jOOQ.
 */
package com.dmoffat.fitnesstracker.db.tables.records;


import com.dmoffat.fitnesstracker.db.tables.WorkoutExercise;

import java.time.LocalDateTime;

import org.jooq.JSON;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class WorkoutExerciseRecord extends UpdatableRecordImpl<WorkoutExerciseRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>ft.workout_exercise.id</code>.
     */
    public void setId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>ft.workout_exercise.id</code>.
     */
    public UInteger getId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>ft.workout_exercise.workout_id</code>.
     */
    public void setWorkoutId(UInteger value) {
        set(1, value);
    }

    /**
     * Getter for <code>ft.workout_exercise.workout_id</code>.
     */
    public UInteger getWorkoutId() {
        return (UInteger) get(1);
    }

    /**
     * Setter for <code>ft.workout_exercise.exercise_id</code>.
     */
    public void setExerciseId(UInteger value) {
        set(2, value);
    }

    /**
     * Getter for <code>ft.workout_exercise.exercise_id</code>.
     */
    public UInteger getExerciseId() {
        return (UInteger) get(2);
    }

    /**
     * Setter for <code>ft.workout_exercise.weight</code>.
     */
    public void setWeight(UInteger value) {
        set(3, value);
    }

    /**
     * Getter for <code>ft.workout_exercise.weight</code>.
     */
    public UInteger getWeight() {
        return (UInteger) get(3);
    }

    /**
     * Setter for <code>ft.workout_exercise.sets</code>.
     */
    public void setSets(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>ft.workout_exercise.sets</code>.
     */
    public Integer getSets() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>ft.workout_exercise.reps</code>.
     */
    public void setReps(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>ft.workout_exercise.reps</code>.
     */
    public Integer getReps() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>ft.workout_exercise.notes</code>.
     */
    public void setNotes(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>ft.workout_exercise.notes</code>.
     */
    public String getNotes() {
        return (String) get(6);
    }

    /**
     * Setter for <code>ft.workout_exercise.equipment</code>.
     */
    public void setEquipment(JSON value) {
        set(7, value);
    }

    /**
     * Getter for <code>ft.workout_exercise.equipment</code>.
     */
    public JSON getEquipment() {
        return (JSON) get(7);
    }

    /**
     * Setter for <code>ft.workout_exercise.created_on</code>.
     */
    public void setCreatedOn(LocalDateTime value) {
        set(8, value);
    }

    /**
     * Getter for <code>ft.workout_exercise.created_on</code>.
     */
    public LocalDateTime getCreatedOn() {
        return (LocalDateTime) get(8);
    }

    /**
     * Setter for <code>ft.workout_exercise.updated_on</code>.
     */
    public void setUpdatedOn(LocalDateTime value) {
        set(9, value);
    }

    /**
     * Getter for <code>ft.workout_exercise.updated_on</code>.
     */
    public LocalDateTime getUpdatedOn() {
        return (LocalDateTime) get(9);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UInteger> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached WorkoutExerciseRecord
     */
    public WorkoutExerciseRecord() {
        super(WorkoutExercise.WORKOUT_EXERCISE);
    }

    /**
     * Create a detached, initialised WorkoutExerciseRecord
     */
    public WorkoutExerciseRecord(UInteger id, UInteger workoutId, UInteger exerciseId, UInteger weight, Integer sets, Integer reps, String notes, JSON equipment, LocalDateTime createdOn, LocalDateTime updatedOn) {
        super(WorkoutExercise.WORKOUT_EXERCISE);

        setId(id);
        setWorkoutId(workoutId);
        setExerciseId(exerciseId);
        setWeight(weight);
        setSets(sets);
        setReps(reps);
        setNotes(notes);
        setEquipment(equipment);
        setCreatedOn(createdOn);
        setUpdatedOn(updatedOn);
        resetChangedOnNotNull();
    }
}