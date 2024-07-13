package com.dmoffat.fitnesstracker.dao;

import com.dmoffat.fitnesstracker.db.tables.records.WorkoutExerciseRecord;
import com.dmoffat.fitnesstracker.model.Exercise;
import com.dmoffat.fitnesstracker.model.User;
import com.dmoffat.fitnesstracker.model.WorkoutExercise;
import org.jooq.DSLContext;
import org.jooq.types.UInteger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.dmoffat.fitnesstracker.db.tables.Workout.WORKOUT;
import static com.dmoffat.fitnesstracker.db.tables.WorkoutExercise.WORKOUT_EXERCISE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WorkoutExerciseDaoTest {
    @Autowired private DSLContext db;
    @Autowired private WorkoutExerciseDao workoutExerciseDao;
    @Autowired private WorkoutDao workoutDao;

    @Test
    @Transactional
    void shouldUpdateSets() {
        // Prepare
        var workout = db.newRecord(WORKOUT);
        workout.setUserId(UInteger.valueOf(1));
        workout.setStartedOn(LocalDateTime.now());
        workout.setCreatedOn(LocalDateTime.now());
        workout.store();

        var workoutExercise = db.newRecord(WORKOUT_EXERCISE);
        workoutExercise.setExerciseId(UInteger.valueOf(1));
        workoutExercise.setWorkoutId(workout.getId());
        workoutExercise.setWeight(UInteger.valueOf(100));
        workoutExercise.setReps(5);
        workoutExercise.setSets(1);
        workoutExercise.setCreatedOn(LocalDateTime.now());
        workoutExercise.store();

        // Execute
        workoutExerciseDao.updateSets(workoutExercise.getId().intValue(), 2);

        // Test
        var savedRecord = findWorkoutExerciseRecord(workoutExercise.getId());
        assertEquals(2, savedRecord.getSets());
    }

    private WorkoutExerciseRecord findWorkoutExerciseRecord(UInteger id) {
        return db.selectFrom(WORKOUT_EXERCISE)
            .where(WORKOUT_EXERCISE.ID.eq(id))
            .fetchOne();
    }

    @Test
    @Transactional
    void shouldCreateNewWorkoutExercise() {
        // Prepare
        var workout = workoutDao.create(1);
        workout.setUser(new User(1));
        var workoutExercise = new WorkoutExercise();
        workoutExercise.setWorkout(workout);
        workoutExercise.setExercise(new Exercise(1));
        workoutExercise.setEquipment(List.of("BELT", "SOMETHING_ELSE"));
        workoutExercise.setWeight(100);
        workoutExercise.setReps(5);
        workoutExercise.setSets(1);
        workoutExercise.setNotes("This is a note");

        // Execute
        Integer id = workoutExerciseDao.create(workoutExercise);

        // Test
        var savedRecord = findWorkoutExerciseRecord(UInteger.valueOf(id));
        assertEquals("[\"BELT\", \"SOMETHING_ELSE\"]", savedRecord.getEquipment().data());
        assertEquals(workout.getId(), savedRecord.getWorkoutId().intValue());
        assertEquals(1, savedRecord.getExerciseId().intValue());
        assertEquals(100, savedRecord.getWeight().intValue());
        assertEquals(5, savedRecord.getReps());
        assertEquals(1, savedRecord.getSets());
        assertEquals("This is a note", savedRecord.getNotes());
        assertNotNull(savedRecord.getCreatedOn());
    }

    @Test
    @Transactional
    void findByWorkoutExerciseIdByWeightRepsAndEquipmentWithEquipment() {
        // Prepare
        var workoutExercise = createWorkoutExerciseWithEquipment(List.of("BELT", "SOMETHING_ELSE"));

        // Execute
        var result = workoutExerciseDao.findByWorkoutExerciseByWeightRepsAndEquipment(workoutExercise);
        assertNotNull(result);
    }

    @Test
    @Transactional
    void findByWorkoutExerciseIdByWeightRepsAndEquipmentWithoutEquipment() {
        // Prepare
        var workoutExercise = createWorkoutExerciseWithEquipment(null);

        // Execute
        var result = workoutExerciseDao.findByWorkoutExerciseByWeightRepsAndEquipment(workoutExercise);
        assertNotNull(result);
    }

    private WorkoutExercise createWorkoutExerciseWithEquipment(List<String> equipment) {
        var workout = workoutDao.create(1);
        workout.setUser(new User(1));
        var workoutExercise = new WorkoutExercise();
        workoutExercise.setWorkout(workout);
        workoutExercise.setExercise(new Exercise(1));
        workoutExercise.setEquipment(equipment);
        workoutExercise.setWeight(100);
        workoutExercise.setReps(5);
        workoutExercise.setSets(1);
        workoutExercise.setNotes("This is a note");

        Integer id = workoutExerciseDao.create(workoutExercise);
        workoutExercise.setId(id);
        return workoutExercise;
    }
}