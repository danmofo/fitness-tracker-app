/*
 * This file is generated by jOOQ.
 */
package com.dmoffat.fitnesstracker.db.tables.records;


import com.dmoffat.fitnesstracker.db.tables.User;

import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserRecord extends UpdatableRecordImpl<UserRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>ft.user.id</code>.
     */
    public void setId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>ft.user.id</code>.
     */
    public UInteger getId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>ft.user.email</code>.
     */
    public void setEmail(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>ft.user.email</code>.
     */
    public String getEmail() {
        return (String) get(1);
    }

    /**
     * Setter for <code>ft.user.password</code>.
     */
    public void setPassword(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>ft.user.password</code>.
     */
    public String getPassword() {
        return (String) get(2);
    }

    /**
     * Setter for <code>ft.user.created_on</code>.
     */
    public void setCreatedOn(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>ft.user.created_on</code>.
     */
    public LocalDateTime getCreatedOn() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>ft.user.updated_on</code>.
     */
    public void setUpdatedOn(LocalDateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>ft.user.updated_on</code>.
     */
    public LocalDateTime getUpdatedOn() {
        return (LocalDateTime) get(4);
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
     * Create a detached UserRecord
     */
    public UserRecord() {
        super(User.USER);
    }

    /**
     * Create a detached, initialised UserRecord
     */
    public UserRecord(UInteger id, String email, String password, LocalDateTime createdOn, LocalDateTime updatedOn) {
        super(User.USER);

        setId(id);
        setEmail(email);
        setPassword(password);
        setCreatedOn(createdOn);
        setUpdatedOn(updatedOn);
        resetChangedOnNotNull();
    }
}