package com.dmoffat.fitnesstracker.dao;

import com.dmoffat.fitnesstracker.model.Workout;
import org.jooq.DSLContext;
import org.jooq.types.UInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.dmoffat.fitnesstracker.db.tables.Workout.WORKOUT;

@Repository
public class WorkoutDao {
    @Autowired private DSLContext db;

    public Workout create(Integer ownerUserId) {
        var workout = db.newRecord(WORKOUT);
        workout.setUserId(UInteger.valueOf(ownerUserId));
        workout.setStartedOn(LocalDateTime.now());
        workout.setCreatedOn(LocalDateTime.now());
        workout.store();

        return workout.into(Workout.class);
    }
}
