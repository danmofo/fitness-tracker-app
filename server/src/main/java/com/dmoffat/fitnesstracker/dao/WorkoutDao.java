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
        var workout = db.newRecord(WORKOUT);
        workout.setUserId(UInteger.valueOf(ownerUserId));
        workout.setStartedOn(LocalDateTime.now());
        workout.setCreatedOn(LocalDateTime.now());
        workout.store();

        return workout.into(Workout.class);
    }

    public Workout findOne(Integer id) {
        return db.select()
            .from(WORKOUT)
            .join(WORKOUT.user())
            .where(WORKOUT.ID.eq(UInteger.valueOf(id)))
            .fetchOneInto(Workout.class);
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
                return workout;
            });
    }
}
