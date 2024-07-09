/*
 * This file is generated by jOOQ.
 */
package com.dmoffat.fitnesstracker.db.tables;


import com.dmoffat.fitnesstracker.db.Ft;
import com.dmoffat.fitnesstracker.db.Keys;
import com.dmoffat.fitnesstracker.db.tables.User.UserPath;
import com.dmoffat.fitnesstracker.db.tables.WorkoutExercise.WorkoutExercisePath;
import com.dmoffat.fitnesstracker.db.tables.records.WorkoutRecord;

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
public class Workout extends TableImpl<WorkoutRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>ft.workout</code>
     */
    public static final Workout WORKOUT = new Workout();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<WorkoutRecord> getRecordType() {
        return WorkoutRecord.class;
    }

    /**
     * The column <code>ft.workout.id</code>.
     */
    public final TableField<WorkoutRecord, UInteger> ID = createField(DSL.name("id"), SQLDataType.INTEGERUNSIGNED.nullable(false).identity(true), this, "");

    /**
     * The column <code>ft.workout.user_id</code>.
     */
    public final TableField<WorkoutRecord, UInteger> USER_ID = createField(DSL.name("user_id"), SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>ft.workout.notes</code>.
     */
    public final TableField<WorkoutRecord, String> NOTES = createField(DSL.name("notes"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>ft.workout.started_on</code>.
     */
    public final TableField<WorkoutRecord, LocalDateTime> STARTED_ON = createField(DSL.name("started_on"), SQLDataType.LOCALDATETIME(0).nullable(false), this, "");

    /**
     * The column <code>ft.workout.finished_on</code>.
     */
    public final TableField<WorkoutRecord, LocalDateTime> FINISHED_ON = createField(DSL.name("finished_on"), SQLDataType.LOCALDATETIME(0), this, "");

    /**
     * The column <code>ft.workout.created_on</code>.
     */
    public final TableField<WorkoutRecord, LocalDateTime> CREATED_ON = createField(DSL.name("created_on"), SQLDataType.LOCALDATETIME(0).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>ft.workout.updated_on</code>.
     */
    public final TableField<WorkoutRecord, LocalDateTime> UPDATED_ON = createField(DSL.name("updated_on"), SQLDataType.LOCALDATETIME(0).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "");

    private Workout(Name alias, Table<WorkoutRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Workout(Name alias, Table<WorkoutRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>ft.workout</code> table reference
     */
    public Workout(String alias) {
        this(DSL.name(alias), WORKOUT);
    }

    /**
     * Create an aliased <code>ft.workout</code> table reference
     */
    public Workout(Name alias) {
        this(alias, WORKOUT);
    }

    /**
     * Create a <code>ft.workout</code> table reference
     */
    public Workout() {
        this(DSL.name("workout"), null);
    }

    public <O extends Record> Workout(Table<O> path, ForeignKey<O, WorkoutRecord> childPath, InverseForeignKey<O, WorkoutRecord> parentPath) {
        super(path, childPath, parentPath, WORKOUT);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class WorkoutPath extends Workout implements Path<WorkoutRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> WorkoutPath(Table<O> path, ForeignKey<O, WorkoutRecord> childPath, InverseForeignKey<O, WorkoutRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private WorkoutPath(Name alias, Table<WorkoutRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public WorkoutPath as(String alias) {
            return new WorkoutPath(DSL.name(alias), this);
        }

        @Override
        public WorkoutPath as(Name alias) {
            return new WorkoutPath(alias, this);
        }

        @Override
        public WorkoutPath as(Table<?> alias) {
            return new WorkoutPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Ft.FT;
    }

    @Override
    public Identity<WorkoutRecord, UInteger> getIdentity() {
        return (Identity<WorkoutRecord, UInteger>) super.getIdentity();
    }

    @Override
    public UniqueKey<WorkoutRecord> getPrimaryKey() {
        return Keys.KEY_WORKOUT_PRIMARY;
    }

    @Override
    public List<ForeignKey<WorkoutRecord, ?>> getReferences() {
        return Arrays.asList(Keys.WORKOUT_USER_ID_FK);
    }

    private transient UserPath _user;

    /**
     * Get the implicit join path to the <code>ft.user</code> table.
     */
    public UserPath user() {
        if (_user == null)
            _user = new UserPath(this, Keys.WORKOUT_USER_ID_FK, null);

        return _user;
    }

    private transient WorkoutExercisePath _workoutExercise;

    /**
     * Get the implicit to-many join path to the
     * <code>ft.workout_exercise</code> table
     */
    public WorkoutExercisePath workoutExercise() {
        if (_workoutExercise == null)
            _workoutExercise = new WorkoutExercisePath(this, null, Keys.WORKOUT_ID_FK.getInverseKey());

        return _workoutExercise;
    }

    @Override
    public Workout as(String alias) {
        return new Workout(DSL.name(alias), this);
    }

    @Override
    public Workout as(Name alias) {
        return new Workout(alias, this);
    }

    @Override
    public Workout as(Table<?> alias) {
        return new Workout(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Workout rename(String name) {
        return new Workout(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Workout rename(Name name) {
        return new Workout(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Workout rename(Table<?> name) {
        return new Workout(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Workout where(Condition condition) {
        return new Workout(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Workout where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Workout where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Workout where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Workout where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Workout where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Workout where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Workout where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Workout whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Workout whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}