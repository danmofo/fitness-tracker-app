package com.dmoffat.fitnesstracker.dao;

import org.jooq.DSLContext;
import org.jooq.types.UInteger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.dmoffat.fitnesstracker.db.tables.Workout.WORKOUT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WorkoutDaoTest {
    @Autowired private DSLContext db;
    @Autowired private WorkoutDao workoutDao;

    @Test
    @Transactional
    void shouldCreateNewWorkout() {
        var workout = workoutDao.create(1);

        // Make sure the record got mapped to our model
        assertEquals(1, workout.getUser().getId());
        assertNotNull(workout.getId());

        // Make sure the workout got added to the database.
        var record = db.selectFrom(WORKOUT)
            .where(WORKOUT.ID.eq(UInteger.valueOf(workout.getId())))
            .fetchOne();

        assertNotNull(record);
        assertNotNull(record.getCreatedOn());
        assertNotNull(record.getStartedOn());
        assertEquals(1, record.getUserId().intValue());
    }


    @Test
    @Transactional
    void shouldFindOneWithUser() {
        // Add a record for a user
        var newWorkout = workoutDao.create(1);

        // Now fetch it
        var workout = workoutDao.findOneWithUser(newWorkout.getId());
        var user = workout.getUser();

        // Make sure our model was mapped
        assertEquals(1, user.getId());
        assertEquals("danmofo@gmail.com", user.getEmail());
        assertEquals(newWorkout.getId(), workout.getId());
    }

    @Test
    @Transactional
    void shouldUpdateFinishedOnAndNotes() {
        // Add a record for a user
        var workout = workoutDao.create(1);

        // Update the record
        var newFinishedOnDate = LocalDateTime.of(2024, 1, 1, 1, 0);
        workoutDao.updateFinishedOnAndNotes(workout.getId(), newFinishedOnDate, "Some notes");

        // Make sure it was updated
        var record = db.selectFrom(WORKOUT)
            .where(WORKOUT.ID.eq(UInteger.valueOf(workout.getId())))
            .fetchOne();

        assertEquals("Some notes", record.getNotes());
        assertEquals(newFinishedOnDate, record.getFinishedOn());
    }
}