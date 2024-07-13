/*
 * This file is generated by jOOQ.
 */
package com.dmoffat.fitnesstracker.db.tables.records;


import com.dmoffat.fitnesstracker.db.tables.Exercise;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class ExerciseRecord extends UpdatableRecordImpl<ExerciseRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>ft.exercise.id</code>.
     */
    public void setId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>ft.exercise.id</code>.
     */
    public UInteger getId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>ft.exercise.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>ft.exercise.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>ft.exercise.brand</code>.
     */
    public void setBrand(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>ft.exercise.brand</code>.
     */
    public String getBrand() {
        return (String) get(2);
    }

    /**
     * Setter for <code>ft.exercise.type</code>.
     */
    public void setType(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>ft.exercise.type</code>.
     */
    public String getType() {
        return (String) get(3);
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
     * Create a detached ExerciseRecord
     */
    public ExerciseRecord() {
        super(Exercise.EXERCISE);
    }

    /**
     * Create a detached, initialised ExerciseRecord
     */
    public ExerciseRecord(UInteger id, String name, String brand, String type) {
        super(Exercise.EXERCISE);

        setId(id);
        setName(name);
        setBrand(brand);
        setType(type);
        resetChangedOnNotNull();
    }
}
