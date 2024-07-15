package com.dmoffat.fitnesstracker.dao;

import com.dmoffat.fitnesstracker.model.User;
import com.dmoffat.fitnesstracker.model.Workout;
import org.jooq.DSLContext;
import org.jooq.types.UInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.dmoffat.fitnesstracker.db.tables.User.USER;
import static com.dmoffat.fitnesstracker.db.tables.Workout.WORKOUT;

@Repository
public class WorkoutDao {
    @Autowired private DSLContext db;

    public Workout create(Integer ownerUserId) {
        // Create the record
        var record = db.newRecord(WORKOUT);
        record.setUserId(UInteger.valueOf(ownerUserId));
        record.setStartedOn(LocalDateTime.now());
        record.setCreatedOn(LocalDateTime.now());
        record.store();

        // Map into our model
        var workout = new Workout();
        workout.setId(record.getId().intValue());
        workout.setUser(new User(ownerUserId));
        return workout;
    }

    public Workout findOneWithUser(Integer id) {
        return db.select()
            .from(WORKOUT)
            .join(WORKOUT.user())
            .where(WORKOUT.ID.eq(UInteger.valueOf(id)))
            .fetchOne(record -> {
                User user = new User();
                user.setId(record.get(USER.ID).intValue());
                user.setEmail(record.get(USER.EMAIL));

                Workout workout = new Workout();
                workout.setId(record.get(WORKOUT.ID).intValue());
                workout.setUser(user);
                workout.setNotes(record.get(WORKOUT.NOTES));
                workout.setStartedOn(record.get(WORKOUT.STARTED_ON));
                workout.setFinishedOn(record.get(WORKOUT.FINISHED_ON));
                return workout;
            });
    }

    public void updateFinishedOnAndNotes(Integer id, LocalDateTime newFinishedOn, String notes) {
        db.update(WORKOUT)
            .set(WORKOUT.FINISHED_ON, newFinishedOn)
            .set(WORKOUT.NOTES, notes)
            .where(WORKOUT.ID.eq(UInteger.valueOf(id)))
            .execute();
    }
}
