/*
 * This file is generated by jOOQ.
 */
package com.dmoffat.fitnesstracker.db.tables;


import com.dmoffat.fitnesstracker.db.Ft;
import com.dmoffat.fitnesstracker.db.Keys;
import com.dmoffat.fitnesstracker.db.tables.BodyWeight.BodyWeightPath;
import com.dmoffat.fitnesstracker.db.tables.Workout.WorkoutPath;
import com.dmoffat.fitnesstracker.db.tables.records.UserRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.types.UInteger;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class User extends TableImpl<UserRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>ft.user</code>
     */
    public static final User USER = new User();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserRecord> getRecordType() {
        return UserRecord.class;
    }

    /**
     * The column <code>ft.user.id</code>.
     */
    public final TableField<UserRecord, UInteger> ID = createField(DSL.name("id"), SQLDataType.INTEGERUNSIGNED.nullable(false).identity(true), this, "");

    /**
     * The column <code>ft.user.email</code>.
     */
    public final TableField<UserRecord, String> EMAIL = createField(DSL.name("email"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>ft.user.password</code>.
     */
    public final TableField<UserRecord, String> PASSWORD = createField(DSL.name("password"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>ft.user.created_on</code>.
     */
    public final TableField<UserRecord, LocalDateTime> CREATED_ON = createField(DSL.name("created_on"), SQLDataType.LOCALDATETIME(0).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>ft.user.updated_on</code>.
     */
    public final TableField<UserRecord, LocalDateTime> UPDATED_ON = createField(DSL.name("updated_on"), SQLDataType.LOCALDATETIME(0).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "");

    private User(Name alias, Table<UserRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private User(Name alias, Table<UserRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>ft.user</code> table reference
     */
    public User(String alias) {
        this(DSL.name(alias), USER);
    }

    /**
     * Create an aliased <code>ft.user</code> table reference
     */
    public User(Name alias) {
        this(alias, USER);
    }

    /**
     * Create a <code>ft.user</code> table reference
     */
    public User() {
        this(DSL.name("user"), null);
    }

    public <O extends Record> User(Table<O> path, ForeignKey<O, UserRecord> childPath, InverseForeignKey<O, UserRecord> parentPath) {
        super(path, childPath, parentPath, USER);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class UserPath extends User implements Path<UserRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> UserPath(Table<O> path, ForeignKey<O, UserRecord> childPath, InverseForeignKey<O, UserRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private UserPath(Name alias, Table<UserRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public UserPath as(String alias) {
            return new UserPath(DSL.name(alias), this);
        }

        @Override
        public UserPath as(Name alias) {
            return new UserPath(alias, this);
        }

        @Override
        public UserPath as(Table<?> alias) {
            return new UserPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Ft.FT;
    }

    @Override
    public Identity<UserRecord, UInteger> getIdentity() {
        return (Identity<UserRecord, UInteger>) super.getIdentity();
    }

    @Override
    public UniqueKey<UserRecord> getPrimaryKey() {
        return Keys.KEY_USER_PRIMARY;
    }

    @Override
    public List<UniqueKey<UserRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_USER_EMAIL);
    }

    private transient BodyWeightPath _bodyWeight;

    /**
     * Get the implicit to-many join path to the <code>ft.body_weight</code>
     * table
     */
    public BodyWeightPath bodyWeight() {
        if (_bodyWeight == null)
            _bodyWeight = new BodyWeightPath(this, null, Keys.BODY_WEIGHT_USER_ID_FK.getInverseKey());

        return _bodyWeight;
    }

    private transient WorkoutPath _workout;

    /**
     * Get the implicit to-many join path to the <code>ft.workout</code> table
     */
    public WorkoutPath workout() {
        if (_workout == null)
            _workout = new WorkoutPath(this, null, Keys.WORKOUT_USER_ID_FK.getInverseKey());

        return _workout;
    }

    @Override
    public User as(String alias) {
        return new User(DSL.name(alias), this);
    }

    @Override
    public User as(Name alias) {
        return new User(alias, this);
    }

    @Override
    public User as(Table<?> alias) {
        return new User(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(String name) {
        return new User(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(Name name) {
        return new User(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(Table<?> name) {
        return new User(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public User where(Condition condition) {
        return new User(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public User where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public User where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public User where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public User where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public User where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public User where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public User where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public User whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public User whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}