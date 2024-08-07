/*
 * This file is generated by jOOQ.
 */
package com.dmoffat.fitnesstracker.db;


import com.dmoffat.fitnesstracker.db.tables.BodyWeight;
import com.dmoffat.fitnesstracker.db.tables.Exercise;
import com.dmoffat.fitnesstracker.db.tables.SpringSession;
import com.dmoffat.fitnesstracker.db.tables.SpringSessionAttributes;
import com.dmoffat.fitnesstracker.db.tables.User;
import com.dmoffat.fitnesstracker.db.tables.Workout;
import com.dmoffat.fitnesstracker.db.tables.WorkoutExercise;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Ft extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>ft</code>
     */
    public static final Ft FT = new Ft();

    /**
     * The table <code>ft.body_weight</code>.
     */
    public final BodyWeight BODY_WEIGHT = BodyWeight.BODY_WEIGHT;

    /**
     * The table <code>ft.exercise</code>.
     */
    public final Exercise EXERCISE = Exercise.EXERCISE;

    /**
     * The table <code>ft.SPRING_SESSION</code>.
     */
    public final SpringSession SPRING_SESSION = SpringSession.SPRING_SESSION;

    /**
     * The table <code>ft.SPRING_SESSION_ATTRIBUTES</code>.
     */
    public final SpringSessionAttributes SPRING_SESSION_ATTRIBUTES = SpringSessionAttributes.SPRING_SESSION_ATTRIBUTES;

    /**
     * The table <code>ft.user</code>.
     */
    public final User USER = User.USER;

    /**
     * The table <code>ft.workout</code>.
     */
    public final Workout WORKOUT = Workout.WORKOUT;

    /**
     * The table <code>ft.workout_exercise</code>.
     */
    public final WorkoutExercise WORKOUT_EXERCISE = WorkoutExercise.WORKOUT_EXERCISE;

    /**
     * No further instances allowed
     */
    private Ft() {
        super("ft", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            BodyWeight.BODY_WEIGHT,
            Exercise.EXERCISE,
            SpringSession.SPRING_SESSION,
            SpringSessionAttributes.SPRING_SESSION_ATTRIBUTES,
            User.USER,
            Workout.WORKOUT,
            WorkoutExercise.WORKOUT_EXERCISE
        );
    }
}
