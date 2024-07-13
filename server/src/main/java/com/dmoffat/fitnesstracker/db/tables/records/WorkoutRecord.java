/*
 * This file is generated by jOOQ.
 */
package com.dmoffat.fitnesstracker.db.tables.records;


import com.dmoffat.fitnesstracker.db.tables.Workout;

import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class WorkoutRecord extends UpdatableRecordImpl<WorkoutRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>ft.workout.id</code>.
     */
    public void setId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>ft.workout.id</code>.
     */
    public UInteger getId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>ft.workout.user_id</code>.
     */
    public void setUserId(UInteger value) {
        set(1, value);
    }

    /**
     * Getter for <code>ft.workout.user_id</code>.
     */
    public UInteger getUserId() {
        return (UInteger) get(1);
    }

    /**
     * Setter for <code>ft.workout.notes</code>.
     */
    public void setNotes(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>ft.workout.notes</code>.
     */
    public String getNotes() {
        return (String) get(2);
    }

    /**
     * Setter for <code>ft.workout.started_on</code>.
     */
    public void setStartedOn(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>ft.workout.started_on</code>.
     */
    public LocalDateTime getStartedOn() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>ft.workout.finished_on</code>.
     */
    public void setFinishedOn(LocalDateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>ft.workout.finished_on</code>.
     */
    public LocalDateTime getFinishedOn() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>ft.workout.created_on</code>.
     */
    public void setCreatedOn(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>ft.workout.created_on</code>.
     */
    public LocalDateTime getCreatedOn() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>ft.workout.updated_on</code>.
     */
    public void setUpdatedOn(LocalDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>ft.workout.updated_on</code>.
     */
    public LocalDateTime getUpdatedOn() {
        return (LocalDateTime) get(6);
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
     * Create a detached WorkoutRecord
     */
    public WorkoutRecord() {
        super(Workout.WORKOUT);
    }

    /**
     * Create a detached, initialised WorkoutRecord
     */
    public WorkoutRecord(UInteger id, UInteger userId, String notes, LocalDateTime startedOn, LocalDateTime finishedOn, LocalDateTime createdOn, LocalDateTime updatedOn) {
        super(Workout.WORKOUT);

        setId(id);
        setUserId(userId);
        setNotes(notes);
        setStartedOn(startedOn);
        setFinishedOn(finishedOn);
        setCreatedOn(createdOn);
        setUpdatedOn(updatedOn);
        resetChangedOnNotNull();
    }
}
